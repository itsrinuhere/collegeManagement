@echo off
call mvn -D skipTests clean assembly:attached
pause