package com.cts.cardcontrol;

import java.io.Serializable;
import java.util.Properties;



import org.hibernate.Session;
import org.hibernate.boot.MappingException;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import com.cts.cardcontrol.bean.entity.Identifiable;


public class StringSequenceIdentifier implements IdentifierGenerator, Configurable{

	public static final String SEQUENCE_PREFIX = "sequence_prefix";

    private String sequencePrefix;

    private String sequenceCallSyntax;

    @Override
    public void configure(
            Type type, Properties params, ServiceRegistry serviceRegistry)
            throws MappingException {
        final JdbcEnvironment jdbcEnvironment =
                serviceRegistry.getService(JdbcEnvironment.class);
        final Dialect dialect = jdbcEnvironment.getDialect();

        sequencePrefix = ConfigurationHelper.getString(
                SEQUENCE_PREFIX,
                params,
                "");

        final String sequencePerEntitySuffix = ConfigurationHelper.getString(
                SequenceStyleGenerator.CONFIG_SEQUENCE_PER_ENTITY_SUFFIX,
                params,
                SequenceStyleGenerator.DEF_SEQUENCE_SUFFIX);

        final String defaultSequenceName = ConfigurationHelper.getBoolean(
                SequenceStyleGenerator.CONFIG_PREFER_SEQUENCE_PER_ENTITY,
                params,
                false)
                ? params.getProperty(JPA_ENTITY_NAME) + sequencePerEntitySuffix
                : SequenceStyleGenerator.DEF_SEQUENCE_NAME;

        sequenceCallSyntax = dialect.getSequenceNextValString(
                ConfigurationHelper.getString(
                        SequenceStyleGenerator.SEQUENCE_PARAM,
                        params,
                        defaultSequenceName));
    }

    @Override
    public Serializable generate(SessionImplementor session, Object obj) {
        if (obj instanceof Identifiable){ 
        	Identifiable<Serializable> entity = (Identifiable) obj;
            Serializable id = entity.getId();
            if (id != null) {
                return id;
            }
        }
        long seqValue = ((Number) Session.class.cast(session)
            .createSQLQuery(sequenceCallSyntax)
            .uniqueResult()).longValue();

        return sequencePrefix + String.format("%011d%s", 0 ,seqValue);
    }
}
