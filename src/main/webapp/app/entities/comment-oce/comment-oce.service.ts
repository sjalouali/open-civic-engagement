import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICommentOce } from 'app/shared/model/comment-oce.model';

type EntityResponseType = HttpResponse<ICommentOce>;
type EntityArrayResponseType = HttpResponse<ICommentOce[]>;

@Injectable({ providedIn: 'root' })
export class CommentOceService {
  public resourceUrl = SERVER_API_URL + 'api/comments';

  constructor(protected http: HttpClient) {}

  create(comment: ICommentOce): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(comment);
    return this.http
      .post<ICommentOce>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(comment: ICommentOce): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(comment);
    return this.http
      .put<ICommentOce>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ICommentOce>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICommentOce[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(comment: ICommentOce): ICommentOce {
    const copy: ICommentOce = Object.assign({}, comment, {
      commentDate: comment.commentDate && comment.commentDate.isValid() ? comment.commentDate.toJSON() : undefined
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
      res.body.forEach((comment: ICommentOce) => {
        comment.commentDate = comment.commentDate ? moment(comment.commentDate) : undefined;
      });
    }
    return res;
  }
}
