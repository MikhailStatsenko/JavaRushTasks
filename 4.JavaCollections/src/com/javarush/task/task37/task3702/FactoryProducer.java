package com.javarush.task.task37.task3702;

import com.javarush.task.task37.task3702.female.FemaleFactory;
import com.javarush.task.task37.task3702.male.MaleFactory;

import javax.swing.plaf.PanelUI;

public class FactoryProducer {
    public static enum HumanFactoryType {
        MALE,
        FEMALE
    }

    public static AbstractFactory getFactory(HumanFactoryType type) {
        AbstractFactory factory = null;
        switch (type) {
            case MALE:
                factory = new MaleFactory();
                break;
            case FEMALE:
                factory = new FemaleFactory();
                break;
        }
        return factory;
    }
}
