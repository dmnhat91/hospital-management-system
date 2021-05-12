# hospital-management-system
A SpringMVC project with JavaScript frontend to manage drug prescriptions

1/ Login Page:
- Users enter their user names and passwords to gain their access to other pages and authorization to request APIs. Assumed these users have been created.

- Sample user account:
    User Name: henry
    Password: 1234
    (Role: ROLE_USER)

- Sample admin account:
    User Name: admin
    Password: 1234
    (Role: ROLE_ADMIN)

- Validation for login form is applied, therefore you must enter correct account and password to gain access.

2/ Visit:
- In this page you can create visit for either new patients or return patients:
    + In terms of new patient, you need to input both information of the new patient and their visit
    + With the return patients, you must input the "Patient ID" field to create the new visit for corresponding patient
(autocomplete is applied to help user find the Patient ID faster when typing Patient Name)
    + All the return patient information is filled already in the "Create visit for return patient" form

- User can search visits by patient ID, patient name, and visit date (autocomplete is applied when searching visit by patient name)
. Moreover, they can also find visits in an interval of days when inserting both "from date" and "to-date" field

- To add and manage prescriptions(ICDs, lab tests, drugs) of a visit, user can click the "Add Prescription" button or open the "Visit Details"
form by clicking "Details" button to add, update, delete Visit - Prescription information

3/ Patient:
- Click "Create new patient" button to directly add a new patient
- User can search patient by name, ID and birthday (autocomplete is applied when searching patient by name)
- Click the "Details" button to open the "Patient Details" form, in which you can view all the selected patient information,
update and delete
- Click "Add Visit" to create a new visit for patient, it directs user to the "Create visit for return patient" form with
all the patient information filled

4/ Drug:
- Only user with "ROLE_ADMIN" can create, update, and delete drug
- User with "ROLE_USER" just can view all available drugs and their details
- All user can search drugs by drug name and drug id, autocomplete is applied when search drugs by name

5/ ICD:
- Only user with "ROLE_ADMIN" can create, update, and delete ICD
- User with "ROLE_USER" just can view all available ICDs and their details
- All user can search ICDs by ICD code, autocomplete is also applied

6/ Medical Service
- Only user with "ROLE_ADMIN" can create, update, and delete medical service
- User with "ROLE_USER" just can view all available medical services and their details
- All user can search medical services by medical service name and id, autocomplete is applied when searching medical services
by name

