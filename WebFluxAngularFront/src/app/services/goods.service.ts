import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GoodsService {
  HOST_URL: string = "http://localhost:8080";
  endSearch: boolean = false;

  constructor(private client: HttpClient) { }

  public getGoods() {
    this.endSearch = false;

    return new Observable(obs => {
      const eventSource = new EventSource(this.HOST_URL + "/goods");

      eventSource.onmessage = (event) => {
        // console.log(event)
        // console.log(event.data)
        obs.next(JSON.parse(event.data));
      }

      eventSource.onopen = (event) => {
        if (this.endSearch) {
          eventSource.close();
          obs.complete();
        } else {
          this.endSearch = true;
        }
      }

      // eventSource.onerror = (error) => {
      //   // obs.error(error);
      //   console.log(error);
      //   eventSource.close();
      // }
    });
  }

  public getGoodsOld(): Observable<any> {
    return this.client.get(this.HOST_URL + "/goods", {headers: new HttpHeaders({ 'Content-Type': 'text/plain' })});
  }

  public getGood(id: number) {
    return this.client.get(this.HOST_URL + "/goods/" + id);
  }

  public saveGood(goodName: string, goodDescription: string, goodImage: string) {
    return this.client.post(this.HOST_URL + "/goods", {name: goodName, description: goodDescription, image: goodImage});
  }

  public updateGood(id: number,goodName: string, goodDescription: string, goodImage: string) {
    return this.client.patch(this.HOST_URL + "/goods/" + id, {name: goodName, description: goodDescription, image: goodImage});
  }

  public deleteGood(id: number) {
    return this.client.delete(this.HOST_URL + "/goods/" + id);
  }
}
