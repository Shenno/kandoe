package be.kdg.kandoe.frontend.config.orika.custom.mappers;

import be.kdg.kandoe.backend.dom.content.Remark;
import be.kdg.kandoe.frontend.controllers.resources.sessions.RemarkResource;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * RemarkMapper is a Mapper to map {link: Remark} to {link: RemarkResource} or in the other direction
 */

@Component
public class RemarkMapper extends CustomMapper<Remark, RemarkResource> {
    @Override
    public void mapBtoA(RemarkResource remarkResource, Remark remark, MappingContext context) {
        super.mapBtoA(remarkResource, remark, context);
    }

    @Override
    public void mapAtoB(Remark remark, RemarkResource remarkResource, MappingContext context) {
        super.mapAtoB(remark, remarkResource, context);
        remarkResource.setUsername(remark.getUser().getUsername());
    }
}
