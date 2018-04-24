package connector;

import request.MicroServiceCommonRequest;

public interface IConnectMq {

    IConnectMq initConnection();

    IConnectMq declareQueue();

    IConnectMq publish(MicroServiceCommonRequest request);

}
