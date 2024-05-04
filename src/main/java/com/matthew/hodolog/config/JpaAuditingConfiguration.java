package com.matthew.hodolog.config;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Component;

@EnableJpaAuditing
@Component
public class JpaAuditingConfiguration {
}
