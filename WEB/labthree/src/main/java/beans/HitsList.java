package beans;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.*;
import models.HitInfo;

import java.io.Serializable;
import java.util.List;

@Named
@Stateless
public class HitsList implements Serializable {
//    @PersistenceContext(unitName = "studsDB")
//    private EntityManager entityManager;

    final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("studsDB");

    @Inject
    private HitForm hitForm;

    public void addNewHit() {
        hitForm.doHit();
        HitInfo hitInfo = hitForm.getHitInfo();

        if (hitInfo != null) {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            try {
                entityManager.persist(hitInfo);
                entityManager.getTransaction().commit();
            } catch (RollbackException ignore) {
                entityManager.getTransaction().rollback();
            }
        }
    }

    public List<HitInfo> hitInfos() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.createQuery("SELECT hitinfo FROM HitInfo hitinfo", HitInfo.class).getResultList();
    }

    public String getPointsData() {
        String data = "";
        for (HitInfo info: hitInfos()) {
            data = data + " " + info.getCoordX() + " " + info.getCoordY() + " " + (info.isStatus() ? "s" : "f");
        }
        return data.trim();
    }
}
