export class TimeStamp{
private year: number;
private month: number;
private day: number;
private hour: number;
private minute: number;
private second: number;

constructor(timeStampString: string){
this.parseTimeStampString(timeStampString);
}

parseTimeStampString(timeStampString: string){

}

public setYear(year: number){this.year = year; }
public setMonth(month: number){this.month = month; }
public setDay(day: number){this.day = day; }
public setHour(hour: number){this.hour = hour; }
public setMinute(minute: number){this.minute = minute; }
public setSecond(second: number){this.second = second; }

public getYear(): number{return this.year; }
public getMonth(): number{return this.month; }
public getDay(): number{return this.day; }
public getHour(): number{return this.hour; }
public getMinute(): number{return this.minute; }
public getSecond(): number{return this.second; }
}
