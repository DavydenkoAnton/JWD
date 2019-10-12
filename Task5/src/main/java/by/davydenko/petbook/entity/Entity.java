package by.davydenko.petbook.entity;

import java.io.Serializable;
import java.util.UUID;

public abstract class Entity implements Serializable {
    private UUID uuid;
    private int id;

    public Entity() {
        uuid = UUID.randomUUID();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Entity entity = (Entity) obj;
        if (this.uuid != null) {
            return this.uuid.equals(entity.uuid);
        }
        return true;
    }

    @Override
    public int hashCode() {
        int prime = 17;
        return this.uuid != null ? this.uuid.hashCode() * prime + id * prime : 0;
    }
}
