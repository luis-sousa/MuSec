/*
 * ESTGF - Escola Superior de Tecnologia e Gestão de Felgueiras 
 * IPP - Instituto Politécnico do Porto 
 * MEI - Mestrado em Engenharia Informática
 * Tese 2015/16 
 */
package model;

/**
 * This class represents a Ossim Server event.
 *
 * @author Luís Sousa - 8090228@estgf.ipp.pt
 * @version 1.0 Jul 15, 2016
 */
public class Event {

    private String id;
    private int priority;
    private int reliability;
    private int asset_src;
    private int asset_dst;
    private int risk;
    private String timestamp;

    /**
     * Construtor.
     */
    public Event() {
    }

    /**
     * Construtor to instanciate an Ossim Event.
     *
     * @param id Event ID.
     * @param priority Event priority value (1-5).
     * @param reliability Event reliability value (1-10).
     * @param asset_src Event Source Host value (1-5).
     * @param asset_dst Event Destination Host value (1-5).
     * @param risk Event risk (1-10).
     * @param timestamp Event date.
     */
    public Event(String id, int priority, int reliability, int asset_src, int asset_dst, int risk, String timestamp) {
        this.id = id;
        this.priority = priority;
        this.reliability = reliability;
        this.asset_src = asset_src;
        this.asset_dst = asset_dst;
        this.risk = risk;
        this.timestamp = timestamp;
    }

    /**
     * Gets an event identification.
     *
     * @return Event ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets an event priority
     *
     * @return Priority of an event
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Gets an event reliability.
     *
     * @return Reliability of an event.
     */
    public int getReliability() {
        return reliability;
    }

    /**
     * Gets an source host value.
     *
     * @return Source host value of an event.
     */
    public int getAsset_src() {
        return asset_src;
    }

    /**
     * Gets an destination host value.
     *
     * @return Destination host value of an event.
     */
    public int getAsset_dst() {
        return asset_dst;
    }

    /**
     * Gets event timestamp.
     *
     * @return Event date (timestamp).
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * Gets an event risk.
     *
     * @return Event risk.
     */
    public int getRisk() {
        return risk;
    }

    /**
     * Changes the event id.
     *
     * @param id Event identification.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Changes the event priority.
     *
     * @param priority Event priority.
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * Changes the event reliability.
     *
     * @param reliability Event reliability.
     */
    public void setReliability(int reliability) {
        this.reliability = reliability;
    }

    /**
     * Changes Source host value.
     *
     * @param asset_src Value of source host.
     */
    public void setAsset_src(int asset_src) {
        this.asset_src = asset_src;
    }

    /**
     * Changes Destination host value.
     *
     * @param asset_dst Value of destination host.
     */
    public void setAsset_dst(int asset_dst) {
        this.asset_dst = asset_dst;
    }

    /**
     * Changes the event timestamp.
     *
     * @param timestamp New timestamp for an event.
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Changes event risk.
     *
     * @param risk Risk of an event.
     */
    public void setRisk(int risk) {
        this.risk = risk;
    }

    /**
     * Prints an Event.
     * @return String with an event.
     */
    @Override
    public String toString() {
        return "Event{" + "id=" + id + ", priority=" + priority + ", reliability=" + reliability + ", asset_src=" + asset_src + ", asset_dst=" + asset_dst + ", risk=" + risk + ", timestamp=" + timestamp + '}';
    }

}
