package com.rachierudragos.dotatracker.Wrapper.domain.filter;

public class QueryStringBuilder {

	public String buildQueryStringForFilter(MatchHistoryFilter filter) {

		if (filter == null)
			return "";

		StringBuilder builder = new StringBuilder();
		builder.append("&");

		for (QueryFilterCriteria criteria : filter.getCriterias()) {

			builder.append(criteria.queryName());
			builder.append("=");
			builder.append(criteria.value());
			if (filter.getCriterias().lastIndexOf(criteria) != filter
					.getCriterias().size() - 1)
				builder.append("&");

		}

		return builder.toString();

	}
}
