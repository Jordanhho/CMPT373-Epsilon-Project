package models.DummyDatabase;

import models.databaseModel.helpers.*;
import models.databaseModel.qualification.*;
import models.databaseModel.qualification.DbUserQualification;
import models.databaseModel.roles.*;
import models.databaseModel.scheduling.*;
import models.queries.TimeUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


// Create a database for testing and development purpose.
// The database consists of 19 users, including one admin and
// 18 users assigned to 3 campuses, and all their related info in other tables.
// Each campus consists of 1 supervisor, 1 team lead, 2 employees and 2 volunteers.
// Order of initialization: 
// - Role before User, and {Role, Permission} before RolePermission
// - {User, Team} before UserTeam
// - ShiftType before Shift
// - {UserTeam, Shift} before UserShift
// - {ShiftType, Qualification} before ShiftQualification
// - {User, Qualification} before UserQualification
// - UserTeam before all type of Availability (OneTime, Recurring, UnAvailablity) 

public class DummyDataBase {

    private int campusTeamSize = 6;
    private final long  RAND_SEED = 1509310291713L;
    private final int ADMIN_ROLE_ID = 1;
    private final int SUPERVISOR_ROLE_ID = 2;
    private final int TEAMLEAD_ROLE_ID = 3;
    private final int EMPLOYEE_ROLE_ID = 4;
    private final int VOLUNTEER_ROLE_ID = 5;
    private final int TEAM_BURNABY_ID = 1;
    private final int TEAM_SURREY_ID = 2;
    private final int TEAM_VANCOUVER_ID = 3;
    private final int PERMISSION_SCHEDULING_ID = 1;
    private final int PERMISSION_USERLIST_ID = 2;
    private final int PERMISSION_STATISTIC_ID = 3;
    private final int PERMISSION_PAYROLL_ID = 4;

    private List<DbRole> roleList = new ArrayList<>();
    private List<DbUser> userList = new ArrayList<>();
    private List<DbTeam> teamList = new ArrayList<>();
    private List<DbShift> shiftList = new ArrayList<>();
    private List<DbShiftType> shiftTypeList = new ArrayList<>();
    private List<DbQualification> qualificationList = new ArrayList<>();

    private List<DbPermission> permissionList = new ArrayList<>();
    private List<DbRolePermission> rolePermissionList = new ArrayList<>();
    private List<DbUserTeam> userTeamList = new ArrayList<>();
    private List<DbUserQualification> userQualificationList = new ArrayList<>();
    private List<DbShiftQualification> shiftQualificationList = new ArrayList<>();
    private List<DbUserShift> userShiftList= new ArrayList<>();
    private List<DbOneTimeAvailability> oneTimeAvailList = new ArrayList<>();

    private Random rand = new Random();


    public DummyDataBase() {

        rand.setSeed(RAND_SEED);
        initRoles();
        //creates roles
        for(DbRole itr: roleList) {
            DbRoleHelper.createDbRole(itr);
        }

        initPermission();
        //creates permission
        for(DbPermission itr: permissionList) {
            DbPermissionHelper.createDbPermission(itr);
        }

        initUsers();
        linkUserRole();
        //creates users
        for(DbUser user : userList) {
            DbUserHelper.createDbUser(user);
        }

        initTeam();
        //creates team
        for(DbTeam itr: teamList) {
            DbTeamHelper.createDbTeam(itr);
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

        initUserTeam();
        //links userTeam
        for(DbUserTeam itr: userTeamList) {
            DbUserTeamHelper.createDbUserTeam(itr);
        }

        initQualification();
        for(DbQualification qualification : qualificationList) {
            DbQualificationHelper.createDbQualification(qualification);
        }

        initRolePermission();
        //links rolepermission
        for(DbRolePermission itr: rolePermissionList) {
            DbRolePermissionHelper.createDbRolePermission(itr);
        }

        initUserQualification(rand);
        //creates userQualification
        for(DbUserQualification itr: userQualificationList) {
            DbUserQualificationHelper.createDbUserQualification(itr);
        }

        initShiftQualification(rand);
        for(DbShiftQualification itr: shiftQualificationList) {
            DbShiftQualificationHelper.createDbShiftQualification(itr);
        }

        initUserShift(rand);
        //creates userShift
        for(DbUserShift itr: userShiftList) {
            DbUserShiftHelper.createDbUserShift(itr);
        }

        initOneTimeAvailabilityList();
        //creates one time availability
        for(DbOneTimeAvailability itr: oneTimeAvailList) {
            DbOneTimeAvailabilityHelper.createDbOneTimeAvailability(itr);
        }
    }

    private void initRoles() {
        roleList.add(new DbRole("admin"));
        roleList.add(new DbRole("supervisor"));
        roleList.add(new DbRole("team lead"));
        roleList.add(new DbRole("employee"));
        roleList.add(new DbRole("volunteer"));
    }

    private void initUsers() {

//        userList.add(new DbUser("Admin", "God", "admin@sfu.ca", "example@email.ca", "(846) 794-8623", "https://www.PhotoUrl.ca"));

        userList.add(new DbUser("Uzziah", "Eyee", "ueyee@sfu.ca", "example@email.ca", "(123)-654-7878", "https://randomuser.me/api/portraits/men/91.jpg"));
        userList.add(new DbUser("Josh", "Vocal", "jhho@sfu.ca", "example@email.ca", "(366)-920-4269", "https://randomuser.me/api/portraits/lego/6.jpg"));
        userList.add(new DbUser("Nicholas", "Fung","ncfung@sfu.ca", "example@email.ca", "(509)-499-0611", "https://randomuser.me/api/portraits/lego/5.jpg"));
        userList.add(new DbUser("Khang", "Bui","pkbui@sfu.ca", "khang.bui12@outlook.com", "6044444444", "https://randomuser.me/api/portraits/lego/4.jpg"));

        //Burnaby 
        userList.add(new DbUser("Gary", "Hernandez","employ1@sfu.ca", "example@email.ca", "(509)-499-0611", "https://www.PhotoUrl.ca"));
        userList.add(new DbUser("Deborah", "Kelly", "employ2@sfu.ca", "example@email.ca", "(509)-499-0611", "https://www.PhotoUrl.ca"));
        userList.add(new DbUser("Larry", "Ross", "volt2@sfu.ca", "example@email.ca", "(777)-685-2470", "https://www.PhotoUrl.ca"));

        //Surrey 
        userList.add(new DbUser("Jeremy", "Wright", "supervisor2@sfu.ca", "example@email.ca", "(730) 639-9920", "https://www.PhotoUrl.ca"));
        userList.add(new DbUser("Elizabeth", "Williams", "teamlead2@sfu.ca", "example@email.ca", "(484) 889-6654", "https://www.PhotoUrl.ca"));
        userList.add(new DbUser("Mildred", "Adams", "employ3@sfu.ca", "example@email.ca", "(135)-558-6155)", "https://www.PhotoUrl.ca"));
        userList.add(new DbUser("Craig", "Turner", "employ4@sfu.ca", "example@email.ca", "(880)-905-9377", "https://www.PhotoUrl.ca"));
        userList.add(new DbUser("Emily", "Mitchell", "volt3@sfu.ca", "example@email.ca", "(136)-989-6296", "https://www.PhotoUrl.ca"));
        userList.add(new DbUser("Virginia", "Hall", "volt4@sfu.ca", "example@email.ca", "(656) 978-2274", "https://www.PhotoUrl.ca"));

        //Vancouver 
        userList.add(new DbUser("Bob", "Builder", "supervisor3@sfu.ca", "example@email.ca", "(577) 732-7675", "https://www.PhotoUrl.ca"));
        userList.add(new DbUser("John", "Cena", "teamlead3@sfu.ca", "example@email.ca", " (901) 552-2492", "https://www.PhotoUrl.ca"));
        userList.add(new DbUser("Judy", "Rivera", "employ5@sfu.ca", "example@email.ca", " (462) 854-5281", "https://www.PhotoUrl.ca"));
        userList.add(new DbUser("Kathryn", "Russell", "employ6@sfu.ca", "example@email.ca", "(588) 618-6391", "https://www.PhotoUrl.ca"));
        userList.add(new DbUser("Michael", "Coleman", "volt5@sfu.ca", "example@email.ca", "(362) 101-6356", "https://www.PhotoUrl.ca"));
        userList.add(new DbUser("Kenneth", "Sanders", "volt6@sfu.ca", "example@email.ca", "(878) 111-5092", "https://www.PhotoUrl.ca"));
    }

    private void initPermission() {
        permissionList.add(new DbPermission("Scheduling"));
        permissionList.add(new DbPermission("User List"));
        permissionList.add(new DbPermission("Statistic"));
        permissionList.add(new DbPermission("Payroll"));
    }

    private void initTeam() {
        teamList.add(new DbTeam("BURNABY"));
        teamList.add(new DbTeam("SURREY"));
        teamList.add(new DbTeam("VANCOUVER"));
    }

    private void initShiftType() {
        shiftTypeList.add(new DbShiftType("Information and Lost&Found Kiosk", " "));
        shiftTypeList.add(new DbShiftType("Speed Watch/Moving Traffic", " "));
        shiftTypeList.add(new DbShiftType("Community Presence", " "));
        shiftTypeList.add(new DbShiftType("Safety Screen", " "));
        shiftTypeList.add(new DbShiftType("Theft Prevention", " "));
        shiftTypeList.add(new DbShiftType("Auto Theft Prevention", " "));
        shiftTypeList.add(new DbShiftType("Bike Presence", " "));
        shiftTypeList.add(new DbShiftType("Special Events (peaceful protests, campus ceremonies, etc)", " "));
        shiftTypeList.add(new DbShiftType("Smoking Checks", " "));
        shiftTypeList.add(new DbShiftType("Pedestrian Safety", " "));
    }

    private void initQualification() {

        qualificationList.add(new DbQualification("Customer Support Training"));
        qualificationList.add(new DbQualification("Transit traffic training"));
        qualificationList.add(new DbQualification("Security Training"));
        qualificationList.add(new DbQualification("Bike/Driver License Training"));
        qualificationList.add(new DbQualification("Manager Training"));
        qualificationList.add(new DbQualification("Common Hazard Training"));
    }

    private void linkUserRole() {
        //only initialized if roles and users are generated
        if(roleList.size() != 0) {
            if (userList.size() != 0) {
                userList.get(0).setRoleId(roleList.get(0).getId());
                int roleId = -1;
                for(int i = 1; i <= campusTeamSize; i++) {
                    if(i == 1){
                        roleId = roleList.get(1).getId();
                    }
                    if(i == 2){
                        roleId = roleList.get(2).getId();
                    }
                    if(i == 3 || i == 4){
                        roleId = roleList.get(3).getId();
                    }
                    if(i == 5 || i == 6){
                        roleId = roleList.get(4).getId();
                    }
                    userList.get(i).setRoleId(roleId);
                    userList.get(i + campusTeamSize).setRoleId(roleId);
                    userList.get(i + campusTeamSize*2).setRoleId(roleId);
                }

                // give custom roles to our sample users
                // 1 => admin, 2 => supervisor, 3 => team lead, 4 => employee, 5 => volunteer

                // Uzziah = Admin
                Integer adminId = roleList.get(0).getId();
                userList.get(0).setRoleId(adminId);

                // Josh = Supervisor
                Integer supId = roleList.get(1).getId();
                userList.get(1).setRoleId(supId);

                // Nicholas = Team Lead
                Integer leadId = roleList.get(2).getId();
                userList.get(2).setRoleId(leadId);

                // Ken = Volunteer
                Integer volId = roleList.get(4).getId();
                userList.get(3).setRoleId(volId);
            }
        }
    }

    private void initRolePermission() {
        rolePermissionList.add(new DbRolePermission(TEAM_BURNABY_ID, ADMIN_ROLE_ID, PERMISSION_SCHEDULING_ID, AccessLevel.DELETE));
        rolePermissionList.add(new DbRolePermission(TEAM_BURNABY_ID, ADMIN_ROLE_ID, PERMISSION_USERLIST_ID, AccessLevel.DELETE));
        rolePermissionList.add(new DbRolePermission(TEAM_BURNABY_ID, ADMIN_ROLE_ID, PERMISSION_STATISTIC_ID, AccessLevel.DELETE));
        rolePermissionList.add(new DbRolePermission(TEAM_BURNABY_ID, ADMIN_ROLE_ID, PERMISSION_PAYROLL_ID, AccessLevel.DELETE));

        for(int i = 1; i <= 3; i++){
            rolePermissionList.add(new DbRolePermission(i, SUPERVISOR_ROLE_ID, PERMISSION_SCHEDULING_ID, AccessLevel.DELETE));
            rolePermissionList.add(new DbRolePermission(i, SUPERVISOR_ROLE_ID, PERMISSION_USERLIST_ID, AccessLevel.DELETE));
            rolePermissionList.add(new DbRolePermission(i, SUPERVISOR_ROLE_ID, PERMISSION_STATISTIC_ID, AccessLevel.DELETE));
            rolePermissionList.add(new DbRolePermission(i, SUPERVISOR_ROLE_ID, PERMISSION_PAYROLL_ID, AccessLevel.DELETE));

            rolePermissionList.add(new DbRolePermission(i, TEAMLEAD_ROLE_ID, PERMISSION_SCHEDULING_ID, AccessLevel.WRITE));
            rolePermissionList.add(new DbRolePermission(i, TEAMLEAD_ROLE_ID, PERMISSION_USERLIST_ID, AccessLevel.WRITE));
            rolePermissionList.add(new DbRolePermission(i, TEAMLEAD_ROLE_ID, PERMISSION_STATISTIC_ID, AccessLevel.WRITE));
            rolePermissionList.add(new DbRolePermission(i, TEAMLEAD_ROLE_ID, PERMISSION_PAYROLL_ID, AccessLevel.WRITE));

            rolePermissionList.add(new DbRolePermission(i, EMPLOYEE_ROLE_ID, PERMISSION_SCHEDULING_ID, AccessLevel.READ));
            rolePermissionList.add(new DbRolePermission(i, EMPLOYEE_ROLE_ID, PERMISSION_USERLIST_ID, AccessLevel.READ));
            rolePermissionList.add(new DbRolePermission(i, EMPLOYEE_ROLE_ID, PERMISSION_STATISTIC_ID, AccessLevel.READ));
            rolePermissionList.add(new DbRolePermission(i, EMPLOYEE_ROLE_ID, PERMISSION_PAYROLL_ID, AccessLevel.READ));

            rolePermissionList.add(new DbRolePermission(i, VOLUNTEER_ROLE_ID, PERMISSION_SCHEDULING_ID, AccessLevel.READ));
            rolePermissionList.add(new DbRolePermission(i, VOLUNTEER_ROLE_ID, PERMISSION_USERLIST_ID, AccessLevel.READ));
            rolePermissionList.add(new DbRolePermission(i, VOLUNTEER_ROLE_ID, PERMISSION_STATISTIC_ID, AccessLevel.READ));
            rolePermissionList.add(new DbRolePermission(i, VOLUNTEER_ROLE_ID, PERMISSION_PAYROLL_ID, AccessLevel.READ));
        }
    }

    private void initUserTeam() {
        //admin doesn't get assigned a team
        for(int i = 1; i < userList.size(); i++){
            if(i < campusTeamSize + 1){
                userTeamList.add(new DbUserTeam(teamList.get(0).getId(),userList.get(i).getId()));
            }
            else{
                if(i < campusTeamSize*2 + 1){
                    userTeamList.add(new DbUserTeam(teamList.get(1).getId(),userList.get(i).getId()));
                }
                else{
                    userTeamList.add(new DbUserTeam(teamList.get(2).getId(),userList.get(i).getId()));
                }
            }
        }
    }

    private void initShift(){
        for(int i = 0; i < 20; i++){
            int day = rand.nextInt(6) + 24;
            shiftList.add(new DbShift
                    (rand.nextInt(shiftTypeList.size()),
                            TimeUtil.getEpochSecondsFromUserInput(2017, 11, day, rand.nextInt(3) + 13, 30),
                            TimeUtil.getEpochSecondsFromUserInput(2017, 11, day, rand.nextInt(2) + 16, 30)
                    ));
        }
    }

    private void initUserShift(Random rand) {
        for(DbUserTeam userTeam : userTeamList){
            userShiftList.add(new DbUserShift(userTeam.getId(), shiftList.get(
                    rand.nextInt(shiftList.size())
            ).getId()));
        }
    }

    private void initOneTimeAvailabilityList() {
        for(int i = 0; i < userTeamList.size(); i++){
            int day = rand.nextInt(6) + 24;
            oneTimeAvailList.add(new DbOneTimeAvailability
                    (userTeamList.get(i).getId(),
                            TimeUtil.getEpochSecondsFromUserInput(2017, 11, day, rand.nextInt(6) + 9, 30),
                            TimeUtil.getEpochSecondsFromUserInput(2017, 11, day, rand.nextInt(3) + 15, 30)
                    ));
        }
    }

    private void initUserQualification(Random rand) {
        Random rand2 = new Random();
        rand2.setSeed(RAND_SEED);
        for(DbUser user: userList){
            if(user.getId() != 0){
                int firstQualifIndex = rand.nextInt(qualificationList.size());
                userQualificationList.add(new DbUserQualification(user.getId(), qualificationList.get(firstQualifIndex).getId()));
                if(rand2.nextInt(2) == 1){
                    int secondQualifIndex = rand.nextInt(qualificationList.size());
                    while(secondQualifIndex == firstQualifIndex){
                        secondQualifIndex = rand.nextInt(qualificationList.size());
                    }
                    userQualificationList.add(new DbUserQualification(user.getId(), qualificationList.get(secondQualifIndex).getId()));
                }
            }
        }
    }

    private void initShiftQualification(Random rand) {
        for(DbShiftType shiftType : shiftTypeList){
            shiftQualificationList.add(new DbShiftQualification(shiftType.getId(), qualificationList.get(
                    rand.nextInt(qualificationList.size())
            ).getId()));
        }
    }
}
