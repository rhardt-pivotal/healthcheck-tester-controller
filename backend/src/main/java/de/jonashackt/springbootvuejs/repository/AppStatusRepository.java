package de.jonashackt.springbootvuejs.repository;


import de.jonashackt.springbootvuejs.domain.AppStatus;
import de.jonashackt.springbootvuejs.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "appstatuses", path = "appstatuses")
public interface AppStatusRepository extends CrudRepository<AppStatus, Long> {

    List<AppStatus> findByAppName(@Param("appname") String appname);
    List<AppStatus> findByAppGuid(@Param("appguid") String appguid);


}
