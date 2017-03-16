package kld.tumanov.aggregator.repository;


public interface SequenceRepository {
    Long getNextSequenceId(String key);
}
