package no.bouvet.reactor.dao;

import no.bouvet.reactor.model.stopplaces.StopPlace;

import java.util.List;

public interface EnturDao {

    List<StopPlace> getStopPlaces();
}
