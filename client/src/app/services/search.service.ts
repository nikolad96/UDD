import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Http } from '@angular/http';
import { Observable } from 'rxjs';
const httpOptions = {
  headers: new HttpHeaders({
    'Access-Control-Allow-Origin':'*'
  })
};
@Injectable({
  providedIn: 'root'
})
export class SearchService {

  constructor(private httpClient: HttpClient, private http : Http) { }

  // saerch1() {
  //   return this.httpClient.get('http://localhost:8080/search/search') as Observable<any>;
  // }
   postSearch1(query) {
    return this.httpClient.post("http://localhost:8080/search/search", query, httpOptions) as Observable<any>;
  }

  postFile(radId, data) {
    return this.http.post("http://localhost:8080/search/post/file/".concat(radId), data) as Observable<any>;
  }
  postSearchMulti(query) {
    return this.httpClient.post("http://localhost:8080/search/multi", query, httpOptions) as Observable<any>;
  }
  postSearchMore(query) {
    return this.httpClient.post("http://localhost:8080/search/more", query, httpOptions) as Observable<any>;
  }
  postSearchGeo(query) {
    return this.httpClient.post("http://localhost:8080/search/geo", query, httpOptions) as Observable<any>;
  }
  getRadovi() {
    return this.httpClient.get('http://localhost:8080/search/get/radovi') as Observable<any>;
  }
}
