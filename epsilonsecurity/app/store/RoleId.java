package store;

public class RoleId
    extends DatabaseId
    implements RoleIdable {

    public RoleId(Integer rawId) {
        super(rawId);
    }

    @Override
    public RoleId getRoleId() {
        return this;
    }

}
