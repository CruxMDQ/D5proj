package com.callisto.d5proj.wizard;

import com.callisto.d5proj.wizard.steps.StatAllocationStep;

import org.codepond.wizardroid.WizardFlow;
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
            .addStep(StatAllocationStep.class)
            .create();
    }
}
