package models.queries;

/**
 * Java Object for generating a form filled with an array of teamIds
 */
public class TeamIdArrayForm {
    private Integer[] teamIdList;

    public void setTeamIdList(Integer[] teamIdList) {
        this.teamIdList = teamIdList;
    }

    public Integer[] getTeamIdList() {
        return teamIdList;
    }
}
