package ru.hh.school.social.impl.services;

import org.springframework.stereotype.Component;
import ru.hh.school.social.impl.entities.Recommendation;
import ru.hh.school.social.exceptions.NoIdException;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Тимур
 * Date: 12.01.12
 * Time: 21:35
 * To change this template use File | Settings | File Templates.
 */
@Component
public class RecommendationsDAO {
    private final Map<Long, Recommendation> recommendations;
    private final Map<Long, LinkedHashSet<Recommendation>> self;
    private final Map<Long, LinkedHashSet<Recommendation>> queries;
    private final Map<Long, LinkedHashSet<Recommendation>> inbox;
    private Long counter;
    public RecommendationsDAO() {
        counter = 0l;
        this.recommendations = new LinkedHashMap<Long, Recommendation>();
        this.self = new HashMap<Long,LinkedHashSet<Recommendation>>();
        this.queries = new HashMap<Long, LinkedHashSet<Recommendation>>();
        this.inbox = new HashMap<Long, LinkedHashSet<Recommendation>>();
    }

    public Iterable<Object> getSelfRecommendations(Long user){
        if (self.containsKey(user))
            return Arrays.asList(self.get(user).toArray());
        return null;
    }
    public Iterable<Object> getInbox(Long user){
        if (inbox.containsKey(user))
            return Arrays.asList(inbox.get(user).toArray());
        return null;
    }

    public Iterable<Object> getQueries(Long user){
        if (queries.containsKey(user))
            return Arrays.asList(queries.get(user).toArray());
        return null;
    }

    public void putQuery(Recommendation recommendation) {
        counter++;
        recommendations.put(counter, recommendation);
        recommendation.setId(counter);
        if (!queries.containsKey(recommendation.getWriter().getId()))
            queries.put(recommendation.getWriter().getId(), new LinkedHashSet<Recommendation>());
        queries.get(recommendation.getWriter().getId()).add(recommendation);
    }

    public Recommendation byId(Long id) throws NoIdException{
        if (recommendations.containsKey(id))
            return recommendations.get(id);
        throw new NoIdException(id);
    }

    public boolean checkAccessRights(Long user, Long id, String part) {
        if (part.equals("inbox"))
            return (recommendations.containsKey(id)&&
                    inbox.containsKey(user)&&
                    inbox.get(user).contains(recommendations.get(id))) ;
        if (part.equals("queries"))
            return (recommendations.containsKey(id)&&
                    queries.containsKey(user)&&
                    queries.get(user).contains(recommendations.get(id))) ;
        if (part.equals("recommendations"))
            return (recommendations.containsKey(id)&&
                    self.containsKey(user)&&
                    self.get(user).contains(recommendations.get(id))) ;
        return false;
    }

    public void removeById(Long id) {
        if (recommendations.containsKey(id)) {
            Recommendation rec = recommendations.get(id);
            if (inbox.containsKey(rec.getSender().getId()))
                inbox.get(rec.getSender().getId()).remove(rec);
            if (self.containsKey(rec.getSender().getId()))
                self.get(rec.getSender().getId()).remove(rec);
            if (queries.containsKey(rec.getWriter().getId()))
                queries.get(rec.getWriter().getId()).remove(rec);
            recommendations.remove(id);
        }
    }

    public void remove(Recommendation rec){
        removeById(rec.getId());
    }

    public boolean existSelf(Long recId, Long userId) {
        return (self.containsKey(userId)&&recommendations.containsKey(recId)&&self.get(userId).contains(recommendations.get(recId)));
    }

    public void processAnswer(Long id, String message){
        if (!recommendations.containsKey(id))
            return;
        Recommendation rec = recommendations.get(id);
        rec.setMessage(message);
        if (queries.containsKey(rec.getWriter().getId())){
            queries.get(rec.getWriter().getId()).remove(rec);
        }
        if (rec.getSelf()){
            if (!self.containsKey(rec.getSender().getId()))
                self.put(rec.getSender().getId(),new LinkedHashSet<Recommendation>());
            self.get(rec.getSender().getId()).add(rec);
        } else {
            if (!inbox.containsKey(rec.getSender().getId()))
                inbox.put(rec.getSender().getId(),new LinkedHashSet<Recommendation>());
            inbox.get(rec.getSender().getId()).add(rec);
        }
    }
}
