Class BI.Test
{
Method m()
{
 zn "%SYS"
 do ##class(Violated).m2()
 do ##class(%ResultSet).m2()
 do ##class(Security.SSLConfigs).m2()
 set status=##class(PatientDetails).CreatePatientDetails(pat.PatientID,pat.Age,genNulls)
}

Method m()
{
 set $Namespace = "%SYS"
 do ##class(%ResultSet).m2()
 do ##class(Security.SSLConfigs).m2()

}
classmethod m2()
{
 do ..m(foo,bar,three)
}
}
