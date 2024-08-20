package com.company.jmixpm.datatype;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import io.jmix.core.metamodel.annotation.DatatypeDef;
import io.jmix.core.metamodel.annotation.Ddl;
import io.jmix.core.metamodel.datatype.Datatype;

import java.text.ParseException;
import java.util.List;
import java.util.Locale;

@DatatypeDef(
        id="projectLabel",
        javaClass = ProjectLabels.class,
        defaultForClass = true
)
@Ddl("varchar(500)")
public class ProjectLabelsDatatype implements Datatype<ProjectLabels> {

    @Override
    public String format(Object value) {
        if (value instanceof ProjectLabels) {
            return Joiner.on(", ")
                    .join(((ProjectLabels) value).getLabels());
        }

        return null;
    }

    @Override
    public String format(Object value, Locale locale) {
        return format(value);
    }

    @Override
    public ProjectLabels parse(String value) throws ParseException {
        if (value == null) {
            return null;
        }

        List<String> labels = Splitter.on(",")
                .omitEmptyStrings()
                .trimResults()
                .splitToList(value);

        return new ProjectLabels(labels);
    }

    @Override
    public ProjectLabels parse(String value, Locale locale) throws ParseException {
        return parse(value);
    }
}
