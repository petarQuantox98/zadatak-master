# zadatak

Dovoljno je samo klonirati projekat i odmah bi trebalo da se run-uje ako masine na kojima se pusta imaju instaliran SDK 11.  

Setovan i za Windows i Linux masine, testiran samo na Windowsu.   

Testovi se mogu pokrenuti i preko TestNG run iz same test klase, a mogu i iz XML suite-a.    

Podrzane platforme su:   
PC - Chrome, Firefox   
Mobile - Chrome Emulator.    

Iz suite-a je moguce run-ovati testove i u headless mode-u.  

Koriscen je POM pattern, princip customizovanih anotacija uz pomoc kojih se fetch-uje element sa obe platforme.  

Princip - jedinstven POM, razliciti testovi za PC i Mob Emulator.  

Pages - klase koje predstavljaju stranice, u ovom slucaju samo Home i Product imaju elemente i metode.  

Tests - testovi za PC i Mob Emulator.  

SiteTools - konstante, grupe, JSON parser, customized waiters.  

Utils - setup files, configs, customized annotations.  

Resources - json sa mobile User Agents - Samsung S8 + XML suite za test run.  
