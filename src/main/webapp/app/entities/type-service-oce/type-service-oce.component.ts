import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiDataUtils } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITypeServiceOce } from 'app/shared/model/type-service-oce.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { TypeServiceOceService } from './type-service-oce.service';
import { TypeServiceOceDeleteDialogComponent } from './type-service-oce-delete-dialog.component';

@Component({
  selector: 'jhi-type-service-oce',
  templateUrl: './type-service-oce.component.html'
})
export class TypeServiceOceComponent implements OnInit, OnDestroy {
  typeServices: ITypeServiceOce[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected typeServiceService: TypeServiceOceService,
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.typeServices = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.typeServiceService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<ITypeServiceOce[]>) => this.paginateTypeServices(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.typeServices = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTypeServices();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITypeServiceOce): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    return this.dataUtils.openFile(contentType, base64String);
  }

  registerChangeInTypeServices(): void {
    this.eventSubscriber = this.eventManager.subscribe('typeServiceListModification', () => this.reset());
  }

  delete(typeService: ITypeServiceOce): void {
    const modalRef = this.modalService.open(TypeServiceOceDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.typeService = typeService;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateTypeServices(data: ITypeServiceOce[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.typeServices.push(data[i]);
      }
    }
  }
}
