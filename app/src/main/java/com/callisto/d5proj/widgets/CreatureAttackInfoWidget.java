package com.callisto.d5proj.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.callisto.d5proj.R;

/**
 * Created by emiliano.desantis on 06/05/2015.
 */
public class CreatureAttackInfoWidget extends LinearLayout{
    private TextView txtWeapon;
    private TextView txtDmg;
    private TextView txtSpecialQualities;

    public CreatureAttackInfoWidget(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray aAttrs = context
            .obtainStyledAttributes(attrs, R.styleable.AttackInfo, 0, 0);

        String weapon = aAttrs.getString(R.styleable.AttackInfo_weapon);
        String specialQualities = aAttrs.getString(R.styleable.AttackInfo_specialQualities);
        int dice = aAttrs.getInteger(R.styleable.AttackInfo_dice, 1);
        int dieSize = aAttrs.getInteger(R.styleable.AttackInfo_dieSize, 6);
        int damageBonus = aAttrs.getInteger(R.styleable.AttackInfo_damageBonus, 0);

        aAttrs.recycle();

        inflateLayout();

        getTxtWeapon().setText(weapon);
        getTxtSpecialQualities().setText(specialQualities);
        getTxtDmg().setText(dice + "d" + dieSize + (damageBonus > 0 ? "+" : "-") + Math.abs(damageBonus));
    }

    private void inflateLayout() {
        inflate(this.getContext(), getLayout(), this);

        setTxtWeapon((TextView) findViewById(R.id.txtWeapon));
        setTxtDmg((TextView) findViewById(R.id.txtDmg));
        setTxtSpecialQualities((TextView) findViewById(R.id.txtSpecialQualities));

    }

    private TextView getTxtDmg() {
        return txtDmg;
    }

    private void setTxtDmg(TextView txtDmg) {
        this.txtDmg = txtDmg;
    }

    private TextView getTxtSpecialQualities() {
        return txtSpecialQualities;
    }

    private void setTxtSpecialQualities(TextView txtSpecialQualities) {
        this.txtSpecialQualities = txtSpecialQualities;
    }

    private TextView getTxtWeapon() {
        return txtWeapon;
    }

    private void setTxtWeapon(TextView txtWeapon) {
        this.txtWeapon = txtWeapon;
    }

    private int getLayout() {
        return R.layout.fragment_frame_attack;
    }
}
