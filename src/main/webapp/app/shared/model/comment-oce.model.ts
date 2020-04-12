import { Moment } from 'moment';

export interface ICommentOce {
  id?: number;
  content?: any;
  commentDate?: Moment;
  userId?: number;
}

export class CommentOce implements ICommentOce {
  constructor(public id?: number, public content?: any, public commentDate?: Moment, public userId?: number) {}
}
