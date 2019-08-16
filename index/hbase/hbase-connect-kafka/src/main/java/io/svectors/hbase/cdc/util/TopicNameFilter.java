package io.svectors.hbase.cdc.util;

import com.google.common.collect.Sets;
import io.svectors.hbase.cdc.config.KafkaConfiguration;

import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

/**
 * @author ravi.magham
 */
public class TopicNameFilter implements Predicate<String> {

    private Set<String> validTopics = Sets.newHashSet();

		public TopicNameFilter(final KafkaConfiguration configuration) {
	    final Optional<String> topics =  configuration.getWhitelistTopics();
			if(topics.isPresent()) {
				final String[] whitelistTopics = topics.toString().split(",");
				for(String topic : whitelistTopics) {
					validTopics.add(topic);
				}
			}
	  }

    /**
     * Checks if the topic name passed is a whitelist.
     * @param topic
     * @return
     */
    @Override
    public boolean test(String topic) {
        return validTopics.isEmpty()
            || validTopics.contains(topic);
    }
}
