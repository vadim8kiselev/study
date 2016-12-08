package com.kiselev.library.action;

public enum ActionEnum {

    AddObjectAction("1", com.kiselev.library.action.impl.AddObjectAction.class),
    DeleteObjectAction("2", com.kiselev.library.action.impl.DeleteObjectAction.class),
    GetObjectAction("3", com.kiselev.library.action.impl.GetObjectAction.class),
    GetObjectsAction("4", com.kiselev.library.action.impl.GetObjectsAction.class),
    FindObjectsAction("5", com.kiselev.library.action.impl.FindObjectAction.class),
    ExitAction("6", com.kiselev.library.action.impl.ExitAction.class);

    private String id;
    private Class className;

    ActionEnum(String id, Class className) {
        this.id = id;
        this.className = className;
    }

    public static Action getAction(String type) {
        try {
            ActionEnum actionEnum = getActionEnum(type);

            if (actionEnum != null) {
                return (Action) actionEnum.className.newInstance();
            } else {
                return null;
            }

        } catch (ReflectiveOperationException error) {
            error.printStackTrace();
            return null;
        }
    }

    private static ActionEnum getActionEnum(String type) {
        for (ActionEnum actionEnum : ActionEnum.values()) {
            if (actionEnum.id.equals(type)) {
                return actionEnum;
            }
        }

        return null;
    }


}
