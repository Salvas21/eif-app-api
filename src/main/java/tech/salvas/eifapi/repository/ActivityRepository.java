package tech.salvas.eifapi.repository;

import org.springframework.stereotype.Repository;
import tech.salvas.eifapi.model.Activity;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ActivityRepository {
    List<Activity> activities = new ArrayList<>();

    public ActivityRepository() {
        activities.add(new Activity("EIFR10", "Pièce de théâtre", "Faire l'écriture d'une pièce de théâtre, pour finalement la présenter", "Beginner"));
        activities.add(new Activity("EIFR12", "Interprétation d'une pièce de théâtre", "Faire l'interprétation d'une pièce de théâtre historique, il faudra pratiquer la diction, et les émotions de l'interprétation", "Avancé"));
        activities.add(new Activity("EIFR11", "Lecture d'un poème", "Faire la lecture d'un roman de votre choix pour mettre en pratique votre interprétation des émotions", "Intermédiaire"));
        activities.add(new Activity("EIFR09", "Analyse d'une pièce de théâtre", "Faire l'analyse d'une pièce de théâtre, sortir les différentes émotions des personnages, leurs motifs", "Débutant"));
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
}
