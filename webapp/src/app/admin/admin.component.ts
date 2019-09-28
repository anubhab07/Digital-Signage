import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatcomponentComponent } from '../matcomponent/matcomponent.component';
import { AppService } from '../app.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss']
})
export class AdminComponent implements OnInit {

  name: string;
  res: string;
  Country: string;
  State: string;
  City: string;
  Area: string;
  Locality: string;
  Desc: string;
  layoutLst;

  constructor(public dialog: MatDialog, private appService: AppService) { }

  openDialog(): void {
    const dialogRef = this.dialog.open(MatcomponentComponent, {
      width: '250px',
      minWidth: '600',
      data: { res: this.res, Country: this.Country, State: this.State, City: this.City, Area: this.Area, Locality: this.Locality, Desc: this.Desc }
    });

    dialogRef.afterClosed().subscribe(result => {
      //console.log(result)
      console.log('The dialog was closed');
    });

  }

  ngOnInit() {
    this.appService.getDashboardData().subscribe((res: any) => {
      console.log(res.data);
      this.layoutLst = res.data;
    });
  }
}





