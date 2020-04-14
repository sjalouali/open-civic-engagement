import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICommentOceOce } from 'app/shared/model/comment-oce-oce.model';

type EntityResponseType = HttpResponse<ICommentOceOce>;
type EntityArrayResponseType = HttpResponse<ICommentOceOce[]>;

@Injectable({ providedIn: 'root' })
export class CommentOceOceService {
  public resourceUrl = SERVER_API_URL + 'api/comment-oces';

  constructor(protected http: HttpClient) {}

  create(commentOce: ICommentOceOce): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(commentOce);
    return this.http
      .post<ICommentOceOce>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(commentOce: ICommentOceOce): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(commentOce);
    return this.http
      .put<ICommentOceOce>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ICommentOceOce>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICommentOceOce[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(commentOce: ICommentOceOce): ICommentOceOce {
    const copy: ICommentOceOce = Object.assign({}, commentOce, {
      commentDate: commentOce.commentDate && commentOce.commentDate.isValid() ? commentOce.commentDate.toJSON() : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.commentDate = res.body.commentDate ? moment(res.body.commentDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((commentOce: ICommentOceOce) => {
        commentOce.commentDate = commentOce.commentDate ? moment(commentOce.commentDate) : undefined;
      });
    }
    return res;
  }
}
