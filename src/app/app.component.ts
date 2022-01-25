import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'careu';
  constructor(private httpClient: HttpClient) {
  }
  ngOnInit() {
    this.httpClient.get(`https://sample-node-serve.herokuapp.com/api/hello`).pipe(
      map((response: any) => {
        debugger;
        return response;
      })
    );
  }
}
