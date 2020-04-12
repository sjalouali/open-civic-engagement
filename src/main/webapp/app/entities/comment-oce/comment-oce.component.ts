import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiDataUtils } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ICommentOce } from 'app/shared/model/comment-oce.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { CommentOceService } from './comment-oce.service';
import { CommentOceDeleteDialogComponent } from './comment-oce-delete-dialog.component';

@Component({
  selector: 'jhi-comment-oce',
  templateUrl: './comment-oce.component.html'
})
export class CommentOceComponent implements OnInit, OnDestroy {
  comments: ICommentOce[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected commentService: CommentOceService,
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.comments = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.commentService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<ICommentOce[]>) => this.paginateComments(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.comments = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInComments();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ICommentOce): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    return this.dataUtils.openFile(contentType, base64String);
  }

  registerChangeInComments(): void {
    this.eventSubscriber = this.eventManager.subscribe('commentListModification', () => this.reset());
  }

  delete(comment: ICommentOce): void {
    const modalRef = this.modalService.open(CommentOceDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.comment = comment;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateComments(data: ICommentOce[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.comments.push(data[i]);
      }
    }
  }
}
