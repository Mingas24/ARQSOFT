// @ts-nocheck

import { InContextSdkMethod } from '@graphql-mesh/types';
import { MeshContext } from '@graphql-mesh/runtime';

export namespace OrderManagementTypes {
  export type Maybe<T> = T | null;
export type InputMaybe<T> = Maybe<T>;
export type Exact<T extends { [key: string]: unknown }> = { [K in keyof T]: T[K] };
export type MakeOptional<T, K extends keyof T> = Omit<T, K> & { [SubKey in K]?: Maybe<T[SubKey]> };
export type MakeMaybe<T, K extends keyof T> = Omit<T, K> & { [SubKey in K]: Maybe<T[SubKey]> };
/** All built-in and custom scalars, mapped to their actual values */
export type Scalars = {
  ID: string;
  String: string;
  Boolean: boolean;
  Int: number;
  Float: number;
};

export type InputOrderDTO = {
  orderDate: Scalars['String'];
  shopId: Scalars['Int'];
  costumerEmail: Scalars['String'];
  sandwiches: Array<InputMaybe<Map>>;
};

export type Map = {
  first?: InputMaybe<Scalars['String']>;
  second?: InputMaybe<Scalars['String']>;
};

export type Mutation = {
  addOrder?: Maybe<OrderDTO>;
  approveOrder?: Maybe<Scalars['Int']>;
  cancelOrder?: Maybe<Scalars['Int']>;
};


export type MutationaddOrderArgs = {
  inputOrderDTO: InputOrderDTO;
};


export type MutationapproveOrderArgs = {
  orderId: Scalars['Float'];
};


export type MutationcancelOrderArgs = {
  orderId: Scalars['Float'];
};

export type OrderDTO = {
  orderDate?: Maybe<Scalars['String']>;
  shopId?: Maybe<Scalars['Float']>;
  costumerEmail?: Maybe<Scalars['String']>;
  status?: Maybe<Scalars['String']>;
};

export type Query = {
  getOrderHistoryByCostumer?: Maybe<Array<Maybe<OrderDTO>>>;
};


export type QuerygetOrderHistoryByCostumerArgs = {
  costumerEmail?: InputMaybe<Scalars['String']>;
};

  export type QuerySdk = {
      /** null **/
  getOrderHistoryByCostumer: InContextSdkMethod<Query['getOrderHistoryByCostumer'], QuerygetOrderHistoryByCostumerArgs, MeshContext>
  };

  export type MutationSdk = {
      /** null **/
  addOrder: InContextSdkMethod<Mutation['addOrder'], MutationaddOrderArgs, MeshContext>,
  /** null **/
  approveOrder: InContextSdkMethod<Mutation['approveOrder'], MutationapproveOrderArgs, MeshContext>,
  /** null **/
  cancelOrder: InContextSdkMethod<Mutation['cancelOrder'], MutationcancelOrderArgs, MeshContext>
  };

  export type SubscriptionSdk = {
    
  };

  export type Context = {
      ["OrderManagement"]: { Query: QuerySdk, Mutation: MutationSdk, Subscription: SubscriptionSdk },
      
    };
}
