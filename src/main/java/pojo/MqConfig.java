package pojo;

public class MqConfig {

    private String host;
    private String user;
    private Integer port;
    private String password;
    private String virtualHost;
    private Integer connectionTimeout;
    private Integer requestedHeartbeat;
    private Integer handshakeTimeout;
    private Integer requestedChannelMax;
    private Integer networkRecoveryInterval;
    private Boolean automaticRecoveryEnabled;
    private Integer basicQos;
    private Integer timeToLive;

    public String getHost() {
        return host;
    }

    public MqConfig setHost(String host) {
        this.host = host;
        return this;
    }

    public String getUser() {
        return user;
    }

    public MqConfig setUser(String user) {
        this.user = user;
        return this;
    }

    public Integer getPort() {
        return port;
    }

    public MqConfig setPort(Integer port) {
        this.port = port;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public MqConfig setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getVirtualHost() {
        return virtualHost;
    }

    public MqConfig setVirtualHost(String virtualHost) {
        this.virtualHost = virtualHost;
        return this;
    }

    public Integer getConnectionTimeout() {
        return connectionTimeout;
    }

    public MqConfig setConnectionTimeout(Integer connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
        return this;
    }

    public Integer getRequestedHeartbeat() {
        return requestedHeartbeat;
    }

    public MqConfig setRequestedHeartbeat(Integer requestedHeartbeat) {
        this.requestedHeartbeat = requestedHeartbeat;
        return this;
    }

    public Integer getHandshakeTimeout() {
        return handshakeTimeout;
    }

    public MqConfig setHandshakeTimeout(Integer handshakeTimeout) {
        this.handshakeTimeout = handshakeTimeout;
        return this;
    }

    public Integer getRequestedChannelMax() {
        return requestedChannelMax;
    }

    public MqConfig setRequestedChannelMax(Integer requestedChannelMax) {
        this.requestedChannelMax = requestedChannelMax;
        return this;
    }

    public Integer getNetworkRecoveryInterval() {
        return networkRecoveryInterval;
    }

    public MqConfig setNetworkRecoveryInterval(Integer networkRecoveryInterval) {
        this.networkRecoveryInterval = networkRecoveryInterval;
        return this;
    }

    public Boolean getAutomaticRecoveryEnabled() {
        return automaticRecoveryEnabled;
    }

    public MqConfig setAutomaticRecoveryEnabled(Boolean automaticRecoveryEnabled) {
        this.automaticRecoveryEnabled = automaticRecoveryEnabled;
        return this;
    }

    public Integer getBasicQos() {
        return basicQos;
    }

    public MqConfig setBasicQos(Integer basicQos) {
        this.basicQos = basicQos;
        return this;
    }

    public Integer getTimeToLive() {
        return timeToLive;
    }

    public MqConfig setTimeToLive(Integer timeToLive) {
        this.timeToLive = timeToLive;
        return this;
    }
}
