package models.DummyDatabase;

import models.databaseModel.helpers.*;
import models.databaseModel.roles.*;
import models.databaseModel.scheduling.*;
import models.queries.TimeUtil;

import java.util.ArrayList;
import java.util.List;

public class DummyDataBase {

    private int burnabyNum = 8;
    private int surreyNum = 7;
    private int vanNum = 5;

    private List<DbUser> userList = new ArrayList<>();
    private List<DbTeam> teamList = new ArrayList<>();
    private List<DbShift> shiftList = new ArrayList<>();
    private List<DbRole> roleList = new ArrayList<>();
    private List<DbPermission> permissionList = new ArrayList<>();
    private List<DbRolePermission> rolePermissionList = new ArrayList<>();
    private List<DbUserTeam> userTeamList = new ArrayList<>();
    private List<DbQualification> qualificationList = new ArrayList<>();
    private List<DbUserQualification> userQualificationList = new ArrayList<>();
    private List<DbShiftQualification> shiftQualificationList = new ArrayList<>();
    private List<DbShiftType> shiftTypeList = new ArrayList<>();
    private List<DbUserShift> userShiftList= new ArrayList<>();
    private List<DbOneTimeAvailability> oneTimeAvailList = new ArrayList<>();




    //20 items
    private void initUsers() {

        //Burnaby 8
        userList.add(new DbUser("Admin", "God", "admin@sfu.ca", "example@email.ca", "(846) 794-8623", "https://www.PhotoUrl.ca"));
        userList.add(new DbUser("Gary", "Hernandez","hern@sfu.ca", "example@email.ca", "(509)-499-0611", "https://www.PhotoUrl.ca"));
        userList.add(new DbUser("Deborah", "Kelly", "kelly@sfu.ca", "example@email.ca", "(509)-499-0611", "https://www.PhotoUrl.ca"));
        userList.add(new DbUser("Scott", "King", "king@sfu.ca", "example@email.ca", "(553)-449-0771", "https://www.PhotoUrl.ca"));
        userList.add(new DbUser("Larry", "Ross", "ross@sfu.ca", "example@email.ca", "(777)-685-2470", "https://www.PhotoUrl.ca"));
        userList.add(new DbUser("Juan", "Roberts", "rob@sfu.ca", "example@email.ca", "(226)-765-6193", "https://www.PhotoUrl.ca"));
        userList.add(new DbUser("Sandra", "Perez", "teamlead@sfu.ca", "example@email.ca", "(366)-920-4269", "https://www.PhotoUrl.ca"));
        userList.add(new DbUser("Theresa", "Henderson", "supervisor2@sfu.ca", "example@email.ca", "(123)-654-7878", "https://www.PhotoUrl.ca"));

        //Surrey 7
        userList.add(new DbUser("Mildred", "Adams", "adam@sfu.ca", "example@email.ca", "(135)-558-6155)", "https://www.PhotoUrl.ca"));
        userList.add(new DbUser("Craig", "Turner", "turn@sfu.ca", "example@email.ca", "(880)-905-9377", "https://www.PhotoUrl.ca"));
        userList.add(new DbUser("Emily", "Mitchell", "mitch@sfu.ca", "example@email.ca", "(136)-989-6296", "https://www.PhotoUrl.ca"));
        userList.add(new DbUser("Virginia", "Hall", "hall@sfu.ca", "example@email.ca", "(656) 978-2274", "https://www.PhotoUrl.ca"));
        userList.add(new DbUser("Kenneth", "Sanders", "sand@sfu.ca", "example@email.ca", "(878) 111-5092", "https://www.PhotoUrl.ca"));
        userList.add(new DbUser("Jeremy", "Wright", "teamlead2@sfu.ca", "example@email.ca", "(730) 639-9920", "https://www.PhotoUrl.ca"));
        userList.add(new DbUser("Elizabeth", "Williams", "supervisor3@sfu.ca", "example@email.ca", "(484) 889-6654", "https://www.PhotoUrl.ca"));

        //Vancouver 5
        userList.add(new DbUser("Judy", "Rivera", "will@sfu.ca", "example@email.ca", " (462) 854-5281", "https://www.PhotoUrl.ca"));
        userList.add(new DbUser("Kathryn", "Russell", "russ@sfu.ca", "example@email.ca", "(588) 618-6391", "https://www.PhotoUrl.ca"));
        userList.add(new DbUser("Michael", "Coleman", "cole@sfu.ca", "example@email.ca", "(362) 101-6356", "https://www.PhotoUrl.ca"));
        userList.add(new DbUser("Bob", "Builder", "teamlead3@sfu.ca", "example@email.ca", "(577) 732-7675", "https://www.PhotoUrl.ca"));
        userList.add(new DbUser("John", "Cena", "supervisor4@sfu.ca", "example@email.ca", " (901) 552-2492", "https://www.PhotoUrl.ca"));

    }



    private void initPermission() {

        //Burnaby 8
        permissionList.add(new DbPermission("admin"));
        permissionList.add(new DbPermission("volunteer"));
        permissionList.add(new DbPermission("volunteer"));
        permissionList.add(new DbPermission("employee"));
        permissionList.add(new DbPermission("employee"));
        permissionList.add(new DbPermission("employee"));
        permissionList.add(new DbPermission("teamLead"));
        permissionList.add(new DbPermission("supervisor"));

        //SURREY 7
        permissionList.add(new DbPermission("volunteer"));
        permissionList.add(new DbPermission("volunteer"));
        permissionList.add(new DbPermission("volunteer"));
        permissionList.add(new DbPermission("employee"));
        permissionList.add(new DbPermission("employee"));
        permissionList.add(new DbPermission("teamLead"));
        permissionList.add(new DbPermission("supervisor"));

        //VANCOUVER 5
        permissionList.add(new DbPermission("volunteer"));
        permissionList.add(new DbPermission("volunteer"));
        permissionList.add(new DbPermission("employee"));
        permissionList.add(new DbPermission("teamLead"));
        permissionList.add(new DbPermission("supervisor"));
    }




    private void initTeam() {
        teamList.add(new DbTeam("BURNABY"));

        teamList.add(new DbTeam("SURREY"));

        teamList.add(new DbTeam("VANCOUVER"));
    }



    private void initRoles() {
        roleList.add(new DbRole("volunteer"));
        roleList.add(new DbRole("volunteer"));
        roleList.add(new DbRole("volunteer"));
        roleList.add(new DbRole("volunteer"));
        roleList.add(new DbRole("volunteer"));
        roleList.add(new DbRole("volunteer"));
        roleList.add(new DbRole("volunteer"));
        roleList.add(new DbRole("volunteer"));
        roleList.add(new DbRole("volunteer"));
        roleList.add(new DbRole("volunteer"));
        roleList.add(new DbRole("volunteer"));
        roleList.add(new DbRole("volunteer"));
        roleList.add(new DbRole("volunteer"));
        roleList.add(new DbRole("volunteer"));
        roleList.add(new DbRole("volunteer"));
        roleList.add(new DbRole("volunteer"));
        roleList.add(new DbRole("volunteer"));
        roleList.add(new DbRole("volunteer"));
        roleList.add(new DbRole("volunteer"));
        roleList.add(new DbRole("volunteer"));
    }



    private void initRolePermission() {

        //burnaby
        int counter = 0;
        rolePermissionList.add(new DbRolePermission(teamList.get(0).getId(), roleList.get(counter).getId(), permissionList.get(counter).getId(), AccessLevel.ADMIN));
        counter++;
        rolePermissionList.add(new DbRolePermission(teamList.get(0).getId(), roleList.get(counter).getId(), permissionList.get(counter).getId(), AccessLevel.VOLUNTEER));
        counter++;
        rolePermissionList.add(new DbRolePermission(teamList.get(0).getId(), roleList.get(counter).getId(), permissionList.get(counter).getId(), AccessLevel.VOLUNTEER));
        counter++;
        rolePermissionList.add(new DbRolePermission(teamList.get(0).getId(), roleList.get(counter).getId(), permissionList.get(counter).getId(), AccessLevel.EMPLOYEE));
        counter++;
        rolePermissionList.add(new DbRolePermission(teamList.get(0).getId(), roleList.get(counter).getId(), permissionList.get(counter).getId(), AccessLevel.EMPLOYEE));
        counter++;
        rolePermissionList.add(new DbRolePermission(teamList.get(0).getId(), roleList.get(counter).getId(), permissionList.get(counter).getId(), AccessLevel.EMPLOYEE));
        counter++;
        rolePermissionList.add(new DbRolePermission(teamList.get(0).getId(), roleList.get(counter).getId(), permissionList.get(counter).getId(), AccessLevel.TEAMLEAD));
        counter++;
        rolePermissionList.add(new DbRolePermission(teamList.get(0).getId(), roleList.get(counter).getId(), permissionList.get(counter).getId(), AccessLevel.SUPERVISOR));
        counter++;

        //surrey
        rolePermissionList.add(new DbRolePermission(teamList.get(1).getId(), roleList.get(counter).getId(), permissionList.get(counter).getId(), AccessLevel.ADMIN));
        counter++;
        rolePermissionList.add(new DbRolePermission(teamList.get(1).getId(), roleList.get(counter).getId(), permissionList.get(counter).getId(), AccessLevel.VOLUNTEER));
        counter++;
        rolePermissionList.add(new DbRolePermission(teamList.get(1).getId(), roleList.get(counter).getId(), permissionList.get(counter).getId(), AccessLevel.VOLUNTEER));
        counter++;
        rolePermissionList.add(new DbRolePermission(teamList.get(1).getId(), roleList.get(counter).getId(), permissionList.get(counter).getId(), AccessLevel.EMPLOYEE));
        counter++;
        rolePermissionList.add(new DbRolePermission(teamList.get(1).getId(), roleList.get(counter).getId(), permissionList.get(counter).getId(), AccessLevel.EMPLOYEE));
        counter++;
        rolePermissionList.add(new DbRolePermission(teamList.get(1).getId(), roleList.get(counter).getId(), permissionList.get(counter).getId(), AccessLevel.TEAMLEAD));
        counter++;
        rolePermissionList.add(new DbRolePermission(teamList.get(1).getId(), roleList.get(counter).getId(), permissionList.get(counter).getId(), AccessLevel.SUPERVISOR));
        counter++;

        //vancouver
        rolePermissionList.add(new DbRolePermission(teamList.get(2).getId(), roleList.get(counter).getId(), permissionList.get(counter).getId(), AccessLevel.VOLUNTEER));
        counter++;
        rolePermissionList.add(new DbRolePermission(teamList.get(2).getId(), roleList.get(counter).getId(), permissionList.get(counter).getId(), AccessLevel.VOLUNTEER));
        counter++;
        rolePermissionList.add(new DbRolePermission(teamList.get(2).getId(), roleList.get(counter).getId(), permissionList.get(counter).getId(), AccessLevel.EMPLOYEE));
        counter++;
        rolePermissionList.add(new DbRolePermission(teamList.get(2).getId(), roleList.get(counter).getId(), permissionList.get(counter).getId(), AccessLevel.TEAMLEAD));
        counter++;
        rolePermissionList.add(new DbRolePermission(teamList.get(2).getId(), roleList.get(counter).getId(), permissionList.get(counter).getId(), AccessLevel.SUPERVISOR));

    }








    private void initQualification() {
        qualificationList.add(new DbQualification("Security GUARD"));
        qualificationList.add(new DbQualification("IT Admin"));
        qualificationList.add(new DbQualification("Self Defence training"));
        qualificationList.add(new DbQualification("Traffic Director"));
        qualificationList.add(new DbQualification("Transit training"));
        qualificationList.add(new DbQualification("Customer Support"));
        qualificationList.add(new DbQualification("Analytic"));
    }


    private void initShiftType() {
        shiftTypeList.add(new DbShiftType("Information and Lost&Found Kiosk"));
        shiftTypeList.add(new DbShiftType("Speed Watch/Moving Traffic"));
        shiftTypeList.add(new DbShiftType("Community Presence"));
        shiftTypeList.add(new DbShiftType("Safety Screen"));
        shiftTypeList.add(new DbShiftType("Theft Prevention"));
        shiftTypeList.add(new DbShiftType("Auto Theft Prevention"));
        shiftTypeList.add(new DbShiftType("Bike Presence"));
        shiftTypeList.add(new DbShiftType("Special Events (peaceful protests, campus ceremonies, etc)"));
        shiftTypeList.add(new DbShiftType("Smoking Checks"));
        shiftTypeList.add(new DbShiftType("Pedestrian Safety"));
    }



    private void initUserTeam() {
        //burnaby
        int counter = 0;
        userTeamList.add(new DbUserTeam(0, userList.get(counter).getId()));
        counter++;
        userTeamList.add(new DbUserTeam(0, userList.get(counter).getId()));
        counter++;
        userTeamList.add(new DbUserTeam(0, userList.get(counter).getId()));
        counter++;
        userTeamList.add(new DbUserTeam(0, userList.get(counter).getId()));
        counter++;
        userTeamList.add(new DbUserTeam(0, userList.get(counter).getId()));
        counter++;
        userTeamList.add(new DbUserTeam(0, userList.get(counter).getId()));
        counter++;
        userTeamList.add(new DbUserTeam(0, userList.get(counter).getId()));
        counter++;
        userTeamList.add(new DbUserTeam(0, userList.get(counter).getId()));
        counter++;

        //surrey
        userTeamList.add(new DbUserTeam(1, userList.get(counter).getId()));
        counter++;
        userTeamList.add(new DbUserTeam(1, userList.get(counter).getId()));
        counter++;
        userTeamList.add(new DbUserTeam(1, userList.get(counter).getId()));
        counter++;
        userTeamList.add(new DbUserTeam(1, userList.get(counter).getId()));
        counter++;
        userTeamList.add(new DbUserTeam(1, userList.get(counter).getId()));
        counter++;
        userTeamList.add(new DbUserTeam(1, userList.get(counter).getId()));
        counter++;
        userTeamList.add(new DbUserTeam(1, userList.get(counter).getId()));
        counter++;

        //vancouver
        userTeamList.add(new DbUserTeam(2, userList.get(counter).getId()));
        counter++;
        userTeamList.add(new DbUserTeam(2, userList.get(counter).getId()));
        counter++;
        userTeamList.add(new DbUserTeam(2, userList.get(counter).getId()));
        counter++;
        userTeamList.add(new DbUserTeam(2, userList.get(counter).getId()));
        counter++;
        userTeamList.add(new DbUserTeam(2, userList.get(counter).getId()));
    }

    private void initShift(){
        shiftList.add(new DbShift(0, TimeUtil.getEpochSecondsFromUserInput(2017, 10,2, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 3, 10, 30)));
        shiftList.add(new DbShift(2, TimeUtil.getEpochSecondsFromUserInput(2017, 10,3, 13, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 4, 15, 30)));
        shiftList.add(new DbShift(0, TimeUtil.getEpochSecondsFromUserInput(2017, 10,4, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 5, 10, 30)));
        shiftList.add(new DbShift(1, TimeUtil.getEpochSecondsFromUserInput(2017, 10,3, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 3, 10, 30)));
        shiftList.add(new DbShift(1, TimeUtil.getEpochSecondsFromUserInput(2017, 10,4, 13, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 4, 15, 30)));

        shiftList.add(new DbShift(0, TimeUtil.getEpochSecondsFromUserInput(2017, 10,5, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 5, 10, 30)));
        shiftList.add(new DbShift(0, TimeUtil.getEpochSecondsFromUserInput(2017, 10,4, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 4, 10, 30)));
        shiftList.add(new DbShift(3, TimeUtil.getEpochSecondsFromUserInput(2017, 10,5, 13, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 5, 15, 30)));
        shiftList.add(new DbShift(9, TimeUtil.getEpochSecondsFromUserInput(2017, 10,6, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 6, 10, 30)));
        shiftList.add(new DbShift(3, TimeUtil.getEpochSecondsFromUserInput(2017, 10,5, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 5, 10, 30)));

        shiftList.add(new DbShift(9, TimeUtil.getEpochSecondsFromUserInput(2017, 10,6, 15, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 6, 18, 30)));
        shiftList.add(new DbShift(5, TimeUtil.getEpochSecondsFromUserInput(2017, 10,2, 13, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 2, 16, 30)));
        shiftList.add(new DbShift(2, TimeUtil.getEpochSecondsFromUserInput(2017, 10,3, 13, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 3, 15, 30)));
        shiftList.add(new DbShift(5, TimeUtil.getEpochSecondsFromUserInput(2017, 10,4, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 4, 10, 30)));
        shiftList.add(new DbShift(1, TimeUtil.getEpochSecondsFromUserInput(2017, 10,4, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 4, 10, 30)));

        shiftList.add(new DbShift(6, TimeUtil.getEpochSecondsFromUserInput(2017, 10,4, 13, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 4, 15, 30)));
        shiftList.add(new DbShift(8, TimeUtil.getEpochSecondsFromUserInput(2017, 10,2, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 2, 10, 30)));
        shiftList.add(new DbShift(5, TimeUtil.getEpochSecondsFromUserInput(2017, 10,2, 13, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 2, 15, 30)));
        shiftList.add(new DbShift(7, TimeUtil.getEpochSecondsFromUserInput(2017, 10,5, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 5, 10, 30)));
        shiftList.add(new DbShift(2, TimeUtil.getEpochSecondsFromUserInput(2017, 10,4, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 4, 10, 30)));

        shiftList.add(new DbShift(9, TimeUtil.getEpochSecondsFromUserInput(2017, 10,3, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 3, 10, 30)));
        shiftList.add(new DbShift(2, TimeUtil.getEpochSecondsFromUserInput(2017, 10,4, 13, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 4, 15, 30)));
        shiftList.add(new DbShift(9, TimeUtil.getEpochSecondsFromUserInput(2017, 10,5, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 5, 10, 30)));
        shiftList.add(new DbShift(9, TimeUtil.getEpochSecondsFromUserInput(2017, 10,3, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 3, 10, 30)));
        shiftList.add(new DbShift(4, TimeUtil.getEpochSecondsFromUserInput(2017, 10,4, 13, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 4, 17, 30)));

        shiftList.add(new DbShift(2, TimeUtil.getEpochSecondsFromUserInput(2017, 10,5, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 5, 10, 30)));
        shiftList.add(new DbShift(2, TimeUtil.getEpochSecondsFromUserInput(2017, 10,2, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 2, 10, 30)));
        shiftList.add(new DbShift(9, TimeUtil.getEpochSecondsFromUserInput(2017, 10,3, 13, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 3, 16, 30)));
        shiftList.add(new DbShift(9, TimeUtil.getEpochSecondsFromUserInput(2017, 10,4, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 4, 10, 30)));
        shiftList.add(new DbShift(4, TimeUtil.getEpochSecondsFromUserInput(2017, 10,4, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 4, 10, 30)));

        shiftList.add(new DbShift(2, TimeUtil.getEpochSecondsFromUserInput(2017, 10,5, 13, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 5, 15, 30)));
        shiftList.add(new DbShift(4, TimeUtil.getEpochSecondsFromUserInput(2017, 10,6, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 6, 10, 30)));
        shiftList.add(new DbShift(2, TimeUtil.getEpochSecondsFromUserInput(2017, 10,4, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 4, 10, 30)));
        shiftList.add(new DbShift(2, TimeUtil.getEpochSecondsFromUserInput(2017, 10,5, 13, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 5, 15, 30)));
        shiftList.add(new DbShift(2, TimeUtil.getEpochSecondsFromUserInput(2017, 10,6, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 6, 10, 30)));

        shiftList.add(new DbShift(7, TimeUtil.getEpochSecondsFromUserInput(2017, 10,3, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 3, 10, 30)));
        shiftList.add(new DbShift(7, TimeUtil.getEpochSecondsFromUserInput(2017, 10,4, 13, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 4, 15, 30)));
        shiftList.add(new DbShift(7, TimeUtil.getEpochSecondsFromUserInput(2017, 10,5, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 5, 10, 30)));
        shiftList.add(new DbShift(9, TimeUtil.getEpochSecondsFromUserInput(2017, 10,2, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 2, 10, 30)));
        shiftList.add(new DbShift(2, TimeUtil.getEpochSecondsFromUserInput(2017, 10,3, 13, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 3, 15, 30)));

        shiftList.add(new DbShift(9, TimeUtil.getEpochSecondsFromUserInput(2017, 10,4, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 4, 10, 30)));
        shiftList.add(new DbShift(9, TimeUtil.getEpochSecondsFromUserInput(2017, 10,5, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 5, 10, 30)));
        shiftList.add(new DbShift(2, TimeUtil.getEpochSecondsFromUserInput(2017, 10,6, 13, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 6, 15, 30)));
        shiftList.add(new DbShift(2, TimeUtil.getEpochSecondsFromUserInput(2017, 10,2, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 2, 10, 30)));
        shiftList.add(new DbShift(4, TimeUtil.getEpochSecondsFromUserInput(2017, 10,3, 13, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 3, 15, 30)));

        shiftList.add(new DbShift(4, TimeUtil.getEpochSecondsFromUserInput(2017, 10,4, 16, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 4, 20, 30)));
        shiftList.add(new DbShift(4, TimeUtil.getEpochSecondsFromUserInput(2017, 10,2, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 2, 10, 30)));
        shiftList.add(new DbShift(4, TimeUtil.getEpochSecondsFromUserInput(2017, 10,3, 13, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 3, 15, 30)));
        shiftList.add(new DbShift(2, TimeUtil.getEpochSecondsFromUserInput(2017, 10,4, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 4, 10, 30)));
        shiftList.add(new DbShift(2, TimeUtil.getEpochSecondsFromUserInput(2017, 10,2, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 2, 10, 30)));

        shiftList.add(new DbShift(2, TimeUtil.getEpochSecondsFromUserInput(2017, 10,3, 14, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 3, 17, 30)));
        shiftList.add(new DbShift(2, TimeUtil.getEpochSecondsFromUserInput(2017, 10,4, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 4, 10, 30)));
        shiftList.add(new DbShift(2, TimeUtil.getEpochSecondsFromUserInput(2017, 10,5, 14, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 5, 17, 30)));
        shiftList.add(new DbShift(2, TimeUtil.getEpochSecondsFromUserInput(2017, 10,6, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 6, 10, 30)));
        shiftList.add(new DbShift(7, TimeUtil.getEpochSecondsFromUserInput(2017, 10,2, 9, 15), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 2, 11, 45)));

        shiftList.add(new DbShift(7, TimeUtil.getEpochSecondsFromUserInput(2017, 10,3, 13, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 3, 16, 45)));
        shiftList.add(new DbShift(7, TimeUtil.getEpochSecondsFromUserInput(2017, 10,4, 13, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 4, 16, 45)));
        shiftList.add(new DbShift(7, TimeUtil.getEpochSecondsFromUserInput(2017, 10,5, 9, 15), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 5, 11, 45)));
        shiftList.add(new DbShift(7, TimeUtil.getEpochSecondsFromUserInput(2017, 10,6, 13, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 6, 16, 45)));
        shiftList.add(new DbShift(7, TimeUtil.getEpochSecondsFromUserInput(2017, 10,6, 13, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 6, 16, 45)));
    }

    private void initUserShift() {

//        for (int counter = 0; counter < userTeamList.size(); counter++) {
//
//            userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(index).getId()));
//            userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(index+1).getId()));
//            userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(index+2).getId()));
//        }

        int counter = 0;
        int counter2 = 0;

        while (counter2 < userTeamList.size()) {
            userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(counter).getId()));
            counter++;

            if (counter % 3 == 0) {
                counter2++;
            }
        }

//
//        //burnaby
//
//        int counter = 0;
//
//        //Person 1:
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
//
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
//
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
//        counter++;
//
//
//
//        //Person 2:
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
//
//
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
//
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
//        counter++;
//
//
//
//        //Person 3:
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
//
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
//
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
//        counter++;
//
//
//
//        //Person 4:
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
//
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
//        counter++;
//
//
//
//        //Person 5:
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
//
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
//
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
//        counter++;
//
//
//
//        //Person 6:
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
//
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
//        counter++;
//
//
//        //Person 7:
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
//
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
//        counter++;
//
//        //Person 8:
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
//        counter++;
//
//
//
//        //surrey
//
//        //Person 9:
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
//        counter++;
//
//
//        //Person 10:
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
//
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
//
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
//        counter++;
//
//
//        //Person 11:
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
//
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
//
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
//        counter++;
//
//
//        //Person 12:
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
//
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
//
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
//        counter++;
//
//
//        //Person 13:
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
//
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
//
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
//        counter++;
//
//
//        //Person 14:
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
//
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
//
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
//        counter++;
//
//        //Person 15:
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
//
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
//
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
//        counter++;
//
//
//
//
//        //vancouver
//
//        //Person 16:
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
//
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
//
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
//
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
//
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
//        counter++;
//
//
//
//        //Person 17:
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
//
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
//
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
//        counter++;
//
//
//        //Person 18:
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
//
//
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
//
//
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
//        counter++;
//
//
//
//        //Person 19:
//
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
//
//
//
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
//
//
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
//
//
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
//
//
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
//        counter++;
//
//
//
//        //Person 20:
//
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
//
//
//
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
//
//
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
//
//
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
//
//
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
//        counter++;
    }
//TODO: ******************************************* FIX THIS LATER ***************************************************
//
//    private void initOneTimeAvailAndUserShift() {
//        int counter = 0;
//
//        //Person 1:
//        counter++;
//
//
//        //Person 2:
//        oneTimeAvailList.add(new DbOneTimeAvailability(counter, TimeUtil.getEpochSecondsFromUserInput(2017, 10,13, 12, 00), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 9, 16, 15)));
//        counter++;
//
//        //Person 3:
//        oneTimeAvailList.add(new DbOneTimeAvailability(counter, TimeUtil.getEpochSecondsFromUserInput(2017, 10,10, 9, 00), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 9, 12, 15)));
//        oneTimeAvailList.add(new DbOneTimeAvailability(counter, TimeUtil.getEpochSecondsFromUserInput(2017, 10,10, 15, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 10, 18, 00)));
//        counter++;
//
//
//
//
//        //Person 4:
//        oneTimeAvailList.add(new DbOneTimeAvailability(counter, TimeUtil.getEpochSecondsFromUserInput(2017, 10,9, 9, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 9, 14, 00)));
//
//        oneTimeAvailList.add(new DbOneTimeAvailability(counter, TimeUtil.getEpochSecondsFromUserInput(2017, 10,10, 14, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 10, 16, 45)));
//
//        oneTimeAvailList.add(new DbOneTimeAvailability(counter, TimeUtil.getEpochSecondsFromUserInput(2017, 10,11, 7, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 11, 12, 00)));
//
//        oneTimeAvailList.add(new DbOneTimeAvailability(counter, TimeUtil.getEpochSecondsFromUserInput(2017, 10,12, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 12, 10, 30)));
//        counter++;
//
//
//        //Person 5:
//        oneTimeAvailList.add(new DbOneTimeAvailability(counter, TimeUtil.getEpochSecondsFromUserInput(2017, 10,10, 9, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 9, 12, 00)));
//
//        counter++;
//
//
//
//        //Person 6:
//        oneTimeAvailList.add(new DbOneTimeAvailability(counter, TimeUtil.getEpochSecondsFromUserInput(2017, 10,11, 7, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 11, 12, 00)));
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), oneTimeAvailList.get( oneTimeAvailList.size()-1).getId()));
//
//        oneTimeAvailList.add(new DbOneTimeAvailability(counter, TimeUtil.getEpochSecondsFromUserInput(2017, 10,12, 9, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 12, 12, 00)));
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), oneTimeAvailList.get( oneTimeAvailList.size()-1).getId()));
//        counter++;
//
//
//
//
//        //Person 7:
//        counter++;
//
//
//        //Person 8:
//        counter++;
//
//
//
//        //surrey
//
//        //Person 9:
//        oneTimeAvailList.add(new DbOneTimeAvailability(counter, TimeUtil.getEpochSecondsFromUserInput(2017, 10,12, 15, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 12, 18, 15)));
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), oneTimeAvailList.get( oneTimeAvailList.size()-1).getId()));
//
//        oneTimeAvailList.add(new DbOneTimeAvailability(counter, TimeUtil.getEpochSecondsFromUserInput(2017, 10,13, 10, 00), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 13, 13, 00)));
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), oneTimeAvailList.get( oneTimeAvailList.size()-1).getId()));
//        counter++;
//
//
//
//        //Person 10:
//        oneTimeAvailList.add(new DbOneTimeAvailability(counter, TimeUtil.getEpochSecondsFromUserInput(2017, 10,9, 9, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 9, 12, 45)));
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), oneTimeAvailList.get( oneTimeAvailList.size()-1).getId()));
//
//        oneTimeAvailList.add(new DbOneTimeAvailability(counter, TimeUtil.getEpochSecondsFromUserInput(2017, 10,10, 9, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 10, 12, 45)));
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), oneTimeAvailList.get( oneTimeAvailList.size()-1).getId()));
//        counter++;
//
//
//
//        //Person 11:
//        oneTimeAvailList.add(new DbOneTimeAvailability(counter, TimeUtil.getEpochSecondsFromUserInput(2017, 10,11, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 11, 13, 45)));
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), oneTimeAvailList.get( oneTimeAvailList.size()-1).getId()));
//
//        oneTimeAvailList.add(new DbOneTimeAvailability(counter, TimeUtil.getEpochSecondsFromUserInput(2017, 10,12, 10, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 12, 14, 15)));
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), oneTimeAvailList.get( oneTimeAvailList.size()-1).getId()));
//        counter++;
//
//
//
//        //Person 12:
//        oneTimeAvailList.add(new DbOneTimeAvailability(counter, TimeUtil.getEpochSecondsFromUserInput(2017, 10,9, 9, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 9, 12, 45)));
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), oneTimeAvailList.get( oneTimeAvailList.size()-1).getId()));
//
//        oneTimeAvailList.add(new DbOneTimeAvailability(counter, TimeUtil.getEpochSecondsFromUserInput(2017, 10,10, 9, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 10, 15, 45)));
//        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), oneTimeAvailList.get( oneTimeAvailList.size()-1).getId()));
//        counter++;
//
//
//
//        //Person 13:
//        oneTimeAvailList.add(new DbOneTimeAvailability(counter, TimeUtil.getEpochSecondsFromUserInput(2017, 10,9, 7, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 9, 12, 15)));
//
//        oneTimeAvailList.add(new DbOneTimeAvailability(counter, TimeUtil.getEpochSecondsFromUserInput(2017, 10,10, 13, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 10, 15, 30)));
//
//        oneTimeAvailList.add(new DbOneTimeAvailability(counter, TimeUtil.getEpochSecondsFromUserInput(2017, 10,11, 9, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 11, 11, 30)));
//        counter++;
//
//
//
//        //Person 14:
//        counter++;
//
//
//
//        //Person 15:
//         counter++;
//
//
//
//        //vancouver
//
//        //Person 16:
//        oneTimeAvailList.add(new DbOneTimeAvailability(counter, TimeUtil.getEpochSecondsFromUserInput(2017, 10,9, 7, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 9, 14, 45)));
//        counter++;
//
//
//        //Person 17:
//        oneTimeAvailList.add(new DbOneTimeAvailability(counter, TimeUtil.getEpochSecondsFromUserInput(2017, 10,12, 10, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 12, 12, 30)));
//        oneTimeAvailList.add(new DbOneTimeAvailability(counter, TimeUtil.getEpochSecondsFromUserInput(2017, 10,13, 14, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 13, 16, 30)));
//        counter++;
//
//
//
//        //Person 18:
//        oneTimeAvailList.add(new DbOneTimeAvailability(counter, TimeUtil.getEpochSecondsFromUserInput(2017, 10,11, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 11, 9, 12, 45)));
//        oneTimeAvailList.add(new DbOneTimeAvailability(counter, TimeUtil.getEpochSecondsFromUserInput(2017, 10,12, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 12, 10, 12, 45)));
//        counter++;
//
//
//        //Person 19:
//        counter++;
//
//
//        //Person 20:
//        counter++;
//    }


    private void initUserQualification() {
        int counter = 0;

        //burnaby
        userQualificationList.add(new DbUserQualification(counter, qualificationList.get(0).getId()));
        counter++;

        userQualificationList.add(new DbUserQualification(counter, qualificationList.get(1).getId()));
        counter++;

        userQualificationList.add(new DbUserQualification(counter, qualificationList.get(2).getId()));
        counter++;

        userQualificationList.add(new DbUserQualification(counter, qualificationList.get(3).getId()));
        counter++;

        userQualificationList.add(new DbUserQualification(counter, qualificationList.get(4).getId()));
        counter++;

        userQualificationList.add(new DbUserQualification(counter, qualificationList.get(5).getId()));
        counter++;

        userQualificationList.add(new DbUserQualification(counter, qualificationList.get(6).getId()));
        counter++;

        userQualificationList.add(new DbUserQualification(counter, qualificationList.get(1).getId()));
        counter++;



        //surrey
        userQualificationList.add(new DbUserQualification(counter, qualificationList.get(0).getId()));
        counter++;

        userQualificationList.add(new DbUserQualification(counter, qualificationList.get(1).getId()));
        counter++;

        userQualificationList.add(new DbUserQualification(counter, qualificationList.get(0).getId()));
        counter++;

        userQualificationList.add(new DbUserQualification(counter, qualificationList.get(2).getId()));
        counter++;

        userQualificationList.add(new DbUserQualification(counter, qualificationList.get(3).getId()));
        counter++;

        userQualificationList.add(new DbUserQualification(counter, qualificationList.get(0).getId()));
        counter++;

        userQualificationList.add(new DbUserQualification(counter, qualificationList.get(4).getId()));
        counter++;


        //vancouver
        userQualificationList.add(new DbUserQualification(counter, qualificationList.get(5).getId()));
        counter++;

        userQualificationList.add(new DbUserQualification(counter, qualificationList.get(2).getId()));
        counter++;

        userQualificationList.add(new DbUserQualification(counter, qualificationList.get(0).getId()));
        counter++;

        userQualificationList.add(new DbUserQualification(counter, qualificationList.get(1).getId()));
        counter++;

        userQualificationList.add(new DbUserQualification(counter, qualificationList.get(4).getId()));
        counter++;
    }


    public DummyDataBase() {

        initRoles();
        //creates roles
        for(DbRole itr: roleList) {
            DbRoleHelper.createDbRole(itr);
        }

        initUsers();
        //creates users
        for(int i = 0; i < userList.size(); i++) {
            userList.get(i).setRoleId(roleList.get(i).getId());
            DbUserHelper.createDbUser(userList.get(i));
        }

        initTeam();
        //creates team
        for(DbTeam itr: teamList) {
            DbTeamHelper.createDbTeam(itr);
        }

        initPermission();
        //creates permission
        for(DbPermission itr: permissionList) {
            DbPermissionHelper.createDbPermission(itr);
        }

        initRolePermission();
        //links rolepermission
        for(DbRolePermission itr: rolePermissionList) {
            DbRolePermissionHelper.createDbRolePermission(itr);
        }

        initUserTeam();
        //links userTeam
        for(DbUserTeam itr: userTeamList) {
            DbUserTeamHelper.createDbUserTeam(itr);
        }

        initQualification();
        //links shift qualifcation
        for(int i = 0; i < shiftTypeList.size(); i++) {
            shiftQualificationList.add(new DbShiftQualification(shiftTypeList.get(i).getId(), qualificationList.get(i).getId()));
            DbShiftQualificationHelper.createDbShiftQualification(shiftQualificationList.get(i));
        }

        //creates qualifcations
        for(DbQualification itr: qualificationList) {
            DbQualificationHelper.createDbQualification(itr);
        }

        initUserQualification();
        //creates userQualification
        for(DbUserQualification itr: userQualificationList) {
            DbUserQualificationHelper.createDbUserQualification(itr);
        }


        initShiftType();
        //creates shiftType
        for(DbShiftType itr: shiftTypeList) {
            DbShiftTypeHelper.createDbShiftType(itr);
        }

        initShift();
        //creates Shift
        for(DbShift itr: shiftList) {
            DbShiftHelper.createDbShift(itr);
        }

        initUserShift();
        //creates userShift
        for(DbUserShift itr: userShiftList) {
            DbUserShiftHelper.createDbUserShift(itr);
        }

        //initOneTimeAvailAndUserShift();
        //creates one time availability
        //for(DbOneTimeAvailability itr: oneTimeAvailList) {
        //    DbOneTimeAvailabilityHelper.createDbOneTimeAvailability(itr);
        //}
    }
}
