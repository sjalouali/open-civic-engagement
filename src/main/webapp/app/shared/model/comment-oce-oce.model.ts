import { Moment } from 'moment';

export interface ICommentOceOce {
  id?: number;
  content?: any;
  commentDate?: Moment;
  uuidOce?: string;
  uuidOrganisation?: string;
  uuidEntity?: string;
  archived?: boolean;
  deleted?: boolean;
  userId?: number;
  actionServiceId?: number;
}

export class CommentOceOce implements ICommentOceOce {
  constructor(
    public id?: number,
    public content?: any,
    public commentDate?: Moment,
    public uuidOce?: string,
    public uuidOrganisation?: string,
    public uuidEntity?: string,
    public archived?: boolean,
    public deleted?: boolean,
    public userId?: number,
    public actionServiceId?: number
  ) {
    this.archived = this.archived || false;
    this.deleted = this.deleted || false;
  }
}
