package main.graph.command;

public enum CommandFactory {

    OutgoingDegreeOfVertex("I 2", main.graph.command.impl.OutgoingDegreeOfVertex.class),
    VertexesWithLoop("I 11", main.graph.command.impl.VertexesWithLoop.class),
    NotConnectedVertexes("I 14", main.graph.command.impl.NotConnectedVertexes.class),
    SubGraphWithoutEvenVertexes("Ib 9", main.graph.command.impl.SubGraphWithoutEvenVertexes.class),
    CyclomaticNumberOfGraph("II 11", main.graph.command.impl.CyclomaticNumberOfGraph.class),
    NumberOfAllPathsFromOneVertexToOthers("II 12", main.graph.command.impl.NumberOfAllPathsFromOneVertexToOthers.class),
    AllPathsFromOneVertexToAnotherVertex("II 13", main.graph.command.impl.AllPathsFromOneVertexToAnotherVertex.class);

    private String id;
    private Class className;

    CommandFactory(String id, Class className) {
        this.id = id;
        this.className = className;
    }

    public static Executor getCommand(String type) {
        try {
            CommandFactory actionEnum = getCommandFactory(type);

            if (actionEnum != null) {
                return (Executor) actionEnum.className.newInstance();
            } else {
                return null;
            }

        } catch (ReflectiveOperationException error) {
            error.printStackTrace();
            return null;
        }
    }

    private static CommandFactory getCommandFactory(String type) {
        for (CommandFactory actionEnum : CommandFactory.values()) {
            if (actionEnum.id.equals(type)) {
                return actionEnum;
            }
        }

        return null;
    }
}
