import {ChangeDetectorRef, Component, OnInit, provideZoneChangeDetection} from '@angular/core';
import {GoodsService} from './services/goods.service';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-root',
  standalone: true,
  // imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
  imports: [
    FormsModule
  ],
  providers: [GoodsService]
})
export class AppComponent implements OnInit {
  good: any;
  goods: any[] = [];
  goodName: string = "test";
  goodDescription: string = "test description";
  goodImage: string = "placeholder.png";

  constructor(private goodsService: GoodsService, private ref: ChangeDetectorRef) {}

  ngOnInit(): void {
    this.getGoods();
  }

  public getGoodsTest() {
    this.goodsService.getGoods().subscribe((res:any) => {
      console.log(res)
    });
    // this.goodsService.getGoodsOld().subscribe((res:any) => {
    //   console.log(res)
    // });

  }

  public getGoods() {
    this.goods = [];
    this.goodsService.getGoods().subscribe((res:any) => {
      console.log(res);
      this.goods.push(res);
      this.ref.detectChanges();
    });
  }

  public getGood(id: number) {
    this.goodsService.getGood(id).subscribe((res:any) => {
      this.good = res;
    });
  }

  public saveGood() {
    this.goodsService.saveGood(this.goodName, this.goodDescription, this.goodImage).subscribe(resp => this.getGoods());
  }

  public deleteGood(id: number) {
    this.goodsService.deleteGood(id).subscribe(resp => this.getGoods());
  }

  public updateGood(id: number) {
    this.goodsService.updateGood(id, this.goodName, this.goodDescription, this.goodImage).subscribe(resp => this.getGoods());
  }

  protected readonly Number = Number;
}
