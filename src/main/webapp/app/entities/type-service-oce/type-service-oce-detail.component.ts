import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { ITypeServiceOce } from 'app/shared/model/type-service-oce.model';

@Component({
  selector: 'jhi-type-service-oce-detail',
  templateUrl: './type-service-oce-detail.component.html'
})
export class TypeServiceOceDetailComponent implements OnInit {
  typeService: ITypeServiceOce | null = null;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ typeService }) => (this.typeService = typeService));
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
