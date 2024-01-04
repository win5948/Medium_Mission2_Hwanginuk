package com.ll.medium.global.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBaseTime is a Querydsl query type for BaseTime
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QBaseTime extends EntityPathBase<BaseTime> {

    private static final long serialVersionUID = -158382685L;

    public static final QBaseTime baseTime = new QBaseTime("baseTime");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final DateTimePath<java.time.LocalDateTime> createDate = createDateTime("createDate", java.time.LocalDateTime.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final DateTimePath<java.time.LocalDateTime> modifyDate = createDateTime("modifyDate", java.time.LocalDateTime.class);

    public QBaseTime(String variable) {
        super(BaseTime.class, forVariable(variable));
    }

    public QBaseTime(Path<? extends BaseTime> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBaseTime(PathMetadata metadata) {
        super(BaseTime.class, metadata);
    }

}

