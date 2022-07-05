This is a README file!
Nathan Moes - Appclasses/PhoneBill
Command line input is [options] <customer> <callerNumber> <calleeNumber> <begin date> <begin time> <end date> <end time>
-README option open readme file from this projects resources
-print will show the information related to a created phonecall class object
Phone call aruments from the command line are as to specification from appcalsses document
that being in order - "Customer name" "caller number" "callee number" "call start date" "call start time" "call end date
" "call end time"
Callee name is left as None, as the project specification says nothing as to how to obtain it from command line
The input from command line will filter the phone number to ensure its correct as well as the dates/times
The name input is not checked other than ensuring the input is using characters and spaces
First a phone bill object for the created phone call object to be added to