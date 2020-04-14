import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiDataUtils } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IActionServiceOce } from 'app/shared/model/action-service-oce.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { ActionServiceOceService } from './action-service-oce.service';
import { ActionServiceOceDeleteDialogComponent } from './action-service-oce-delete-dialog.component';

@Component({
  selector: 'jhi-action-service-oce',
  templateUrl: './action-service-oce.component.html'
})
export class ActionServiceOceComponent implements OnInit, OnDestroy {
  actionServices: IActionServiceOce[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected actionServiceService: ActionServiceOceService,
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.actionServices = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.actionServiceService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IActionServiceOce[]>) => this.paginateActionServices(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.actionServices = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInActionServices();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IActionServiceOce): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    return this.dataUtils.openFile(contentType, base64String);
  }

  registerChangeInActionServices(): void {
    this.eventSubscriber = this.eventManager.subscribe('actionServiceListModification', () => this.reset());
  }

  delete(actionService: IActionServiceOce): void {
    const modalRef = this.modalService.open(ActionServiceOceDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.actionService = actionService;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateActionServices(data: IActionServiceOce[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.actionServices.push(data[i]);
      }
    }
  }
}
