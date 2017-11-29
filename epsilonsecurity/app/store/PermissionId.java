package store;

public final class PermissionId extends DatabaseId {

    public PermissionId(Integer rawId) {
        super(rawId);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (!PermissionId.class.isAssignableFrom(obj.getClass())) { return false; }
        final PermissionId other = (PermissionId) obj;
        return other.rawId.equals(this.rawId);
    }
}
