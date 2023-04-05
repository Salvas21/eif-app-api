package tech.salvas.eifapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Choice {
    private Activity activity;
    private int preference;
    private boolean selected;

    public Choice(Activity activity, int preference) {
        this.activity = activity;
        this.preference = preference;
        this.selected = false;
    }
}
