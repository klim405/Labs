package servlet;


import jakarta.persistence.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import json.HitInfoJson;
import json.LoginStatus;
import json.StatusJson;
import models.HitInfo;
import models.Userdata;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/")
public class RestAPI {
    final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("studsDB");

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("userlist")
    public ArrayList<String> getUserList() {
        ArrayList<String> result = new ArrayList<>();
        for (Userdata user : getUsers()) {
            result.add(user.getUsername());
        }
        return result;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("trylogin/{username}/{pwd}")
    public LoginStatus tryLogin(@PathParam("username") String username, @PathParam("pwd") String pwd) {
        LoginStatus status = new LoginStatus();
        List<Userdata> userdataSet = entityManagerFactory.createEntityManager().createQuery("SELECT userdata FROM Userdata userdata", Userdata.class).getResultList();
        userdataSet = userdataSet.stream().filter(
                userdata -> userdata.getUsername().equals(username)
        ).collect(Collectors.toList());

        if (userdataSet.size() > 0) {
            status.setWrongLogin(false);
            if(userdataSet.get(0).getPassword().isEqual(pwd)) {
                status.setWrongPassword(false);
                status.setAuthIsSuccess(true);
            }
        }
        return status;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("adduser/{username}/{pwd}")
    public StatusJson addUser(@PathParam("username") String username, @PathParam("pwd") String pwd) {
        StatusJson statusJson = new StatusJson(false);
        try {
            if (username != null && pwd != null && !username.isEmpty() && !pwd.isEmpty()) {
                Userdata userdata = new Userdata(username, pwd);
                if (saveEntity(userdata)) statusJson.setStatus(true);
            }
        } catch (Exception ignore) {}
        return statusJson;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("addhit/{username}/{x}/{y}/{r}")
    public HitInfo addUser(@PathParam("username") String username, @PathParam("x") String x,
                            @PathParam("y") String y, @PathParam("r") String r) {
        try {
            Double coordX = Double.parseDouble(x);
            Double coordY = Double.parseDouble(y);
            Double radius = Double.parseDouble(r);

            Userdata user = getUser(username);
            HitInfo hitInfo = new HitInfo();
            hitInfo.setUserdata(user);
            hitInfo.setCurrentDate();
            hitInfo.setCoordX(coordX);
            hitInfo.setCoordY(coordY);
            hitInfo.setRadius(radius);
            hitInfo.checkHitAndSetStatus();
            return saveEntity(hitInfo) ? hitInfo : null;
        } catch (NumberFormatException ignore) {
            return null;
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("hits")
    public List<HitInfoJson> getAllHits() {
        return getHitInfos().stream().map(hitInfo -> new HitInfoJson(
                hitInfo.getId(),
                hitInfo.getCoordX(),
                hitInfo.getCoordY(),
                Math.round(hitInfo.getRadius()),
                hitInfo.getHit(),
                hitInfo.getFormatTime()
        )).collect(Collectors.toList());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("hits/{username}")
    public List<HitInfoJson> getUserHits(@PathParam("username") String username) {
        return sortByUser(getHitInfos(), username).stream().map(hitInfo -> new HitInfoJson(
                hitInfo.getId(),
                hitInfo.getCoordX(),
                hitInfo.getCoordY(),
                Math.round(hitInfo.getRadius()),
                hitInfo.getHit(),
                hitInfo.getFormatTime()
        )).collect(Collectors.toList());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("hits/{username}/{r}")
    public List<HitInfo> getHits(@PathParam("username") String username, @PathParam("r") String r) {
        try {
            Double radius = Double.parseDouble(r);
            return sortByRadius(sortByUser(getHitInfos(), username), radius);
        } catch (NumberFormatException ignore) {
            return null;
        }
    }

    protected Userdata getUser(String username) {
        for (Userdata user : getUsers()) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    protected boolean saveEntity(Object entity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
            return true;
        } catch (RollbackException ignore) {
            entityManager.getTransaction().rollback();
            return false;
        }
    }

    protected List<Userdata> getUsers() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.createQuery("SELECT userdata FROM Userdata userdata", Userdata.class).getResultList();
    }

    protected List<HitInfo> sortByUser(List<HitInfo> hits, String username) {
        return hits.stream().filter(
                hitInfo -> hitInfo.getUserdata().getUsername().equals(username)
        ).collect(Collectors.toList());
    }

    protected List<HitInfo> sortByRadius(List<HitInfo> hits, Double radius) {
        return hits.stream().filter(
                hitInfo -> hitInfo.getRadius().equals(radius)
        ).collect(Collectors.toList());
    }

    protected List<HitInfo> getHitInfos() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.createQuery("SELECT hitInfos FROM HitInfo hitInfos", HitInfo.class).getResultList();
    }
}
