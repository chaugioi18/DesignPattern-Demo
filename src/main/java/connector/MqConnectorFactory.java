package connector;

public class MqConnectorFactory {

    public static IConnectMq getConnector(IConnectMq connector) {
        return connector.initConnection();
    }

}
