package tech.salvas.eifapi.repositories;

import org.springframework.stereotype.Repository;
import tech.salvas.eifapi.dtos.ActivityDTO;
import tech.salvas.eifapi.models.Activity;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ActivityRepository {
    List<Activity> activities = new ArrayList<>();

    public ActivityRepository() {
        activities.add(new Activity("EIFR10", "Pièce de théâtre", "Faire l'écriture d'une pièce de théâtre, pour finalement la présenter", "Débutant"));
        activities.add(new Activity("EIFR12", "Interprétation d'une pièce de théâtre", "Faire l'interprétation d'une pièce de théâtre historique, il faudra pratiquer la diction, et les émotions de l'interprétation", "Avancé"));
        activities.add(new Activity("EIFR11", "Lecture d'un poème", "Faire la lecture d'un roman de votre choix pour mettre en pratique votre interprétation des émotions", "Intermédiaire"));
        activities.add(new Activity("EIFR09", "Analyse d'une pièce de théâtre", "Faire l'analyse d'une pièce de théâtre, sortir les différentes émotions des personnages, leurs motifs", "Débutant"));
        activities.add(new Activity("ACT12", "Test", "Description", "Intermédiaire"));
        activities.add(new Activity("BEG11", "Beginner", "A beginner task", "Débutant"));
        activities.add(new Activity("BEG22", "Another", "An other beginner task", "Débutant"));
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public Activity getActivity(String code) {
        for (var activity: activities) {
            if (activity.getCode().equalsIgnoreCase(code))
                return activity;
        }
        return new Activity();
    }

    public List<Activity> getActivitiesFor(String cp) {
        // I know its scummy but it works for now
        var arrayList = new ArrayList<Activity>();
        if (cp.equalsIgnoreCase("SANM12345678")) {
            arrayList.add(activities.get(4));
            return arrayList;
        }
        arrayList.add(activities.get(5));
        arrayList.add(activities.get(6));
        arrayList.add(activities.get(2));
        return arrayList;
    }

    public boolean add(ActivityDTO activityDTO) {
        activities.add(new Activity(activityDTO.getCode(), activityDTO.getName(), activityDTO.getDescription(), activityDTO.getLevel()));
        return true;
    }

    public boolean modify(ActivityDTO activityDTO, String code) {
        for (int i = 0; i< activities.size(); i++) {
            if (activities.get(i).getCode().equalsIgnoreCase(code)) {
                var newActivity = new Activity(activityDTO.getCode(), activityDTO.getName(), activityDTO.getDescription(), activityDTO.getLevel());
                activities.set(i, newActivity);
                return true;
            }
        };
        return false;
    }

    public List<Activity> getActivitiesForLevel(int level) {
        var available = new ArrayList<Activity>();
        for (var activity: activities) {
            if (activity.getActivityLevel() <= level)
                available.add(activity);
        }
        return available;
    }
}
