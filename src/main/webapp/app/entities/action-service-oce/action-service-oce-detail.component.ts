import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IActionServiceOce } from 'app/shared/model/action-service-oce.model';

@Component({
  selector: 'jhi-action-service-oce-detail',
  templateUrl: './action-service-oce-detail.component.html'
})
export class ActionServiceOceDetailComponent implements OnInit {
  actionService: IActionServiceOce | null = null;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ actionService }) => (this.actionService = actionService));
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  previousState(): void {
    window.history.back();
  }
}
