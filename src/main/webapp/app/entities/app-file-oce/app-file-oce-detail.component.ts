import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAppFileOce } from 'app/shared/model/app-file-oce.model';

@Component({
  selector: 'jhi-app-file-oce-detail',
  templateUrl: './app-file-oce-detail.component.html'
})
export class AppFileOceDetailComponent implements OnInit {
  appFile: IAppFileOce | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ appFile }) => (this.appFile = appFile));
  }

  previousState(): void {
    window.history.back();
  }
}
