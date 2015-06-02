package com.callisto.d5proj.wizard;

import android.view.View;

import com.callisto.d5proj.wizard.steps.RaceSelectionStep;
import com.callisto.d5proj.wizard.steps.StatAllocationStep;

import org.codepond.wizardroid.WizardFlow;
import org.codepond.wizardroid.WizardStep;
import org.codepond.wizardroid.layouts.BasicWizardLayout;

/**
 * Created by emiliano.desantis on 07/05/2015.
 * Work still ongoing.
 */
public class CharacterCreationWizard extends BasicWizardLayout {

    public CharacterCreationWizard() { super(); }

    @Override
    public WizardFlow onSetup() {
        setNextButtonText("Next");
        setBackButtonText("Previous");
        setFinishButtonText("Finish");

        return new WizardFlow.Builder()
            .addStep(RaceSelectionStep.class)
            .addStep(StatAllocationStep.class)
            .create();
    }

    @Override
    public void onStepChanged() {
        super.onStepChanged();
        WizardStep step = wizard.getCurrentStep();

        if (step instanceof StatAllocationStep) {
            ((StatAllocationStep) step).getRaceFromPrefs();
        }
    }

    @Override
    public void onClick(View view) {
        WizardStep step = wizard.getCurrentStep();

        if (step instanceof StatAllocationStep) {
            ((StatAllocationStep) step).reset();
        }
        super.onClick(view);
    }
}
