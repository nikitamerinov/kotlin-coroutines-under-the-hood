import {Img, Layout, makeScene2D, Node, Ray, Txt} from '@motion-canvas/2d';
import {all, createRef, delay, Origin, waitFor, waitUntil} from '@motion-canvas/core';
import avatarSrc from '/assets/images/avatar.png';
import {Caption, Text} from '../../common';

export default makeScene2D(function* (view) {
  const upperLabel = createRef<Txt>();
  const lowerLabel = createRef<Txt>();
  const lowerLabelWrapper = createRef<Node>();
  const line = createRef<Ray>();
  const avatar = createRef<Img>();
  const number = createRef<Txt>();

  view.add(
    <>
      <Layout layout alignItems={'center'} gap={20}>
        <Layout direction={'column'} alignItems={'center'} gap={15}>
          <Caption ref={upperLabel} text={'Kotlin coroutines'} />
          <Ray ref={line} lineWidth={5} stroke={'#9699a0'} />
          <Layout clip>
            <Node ref={lowerLabelWrapper}>
              <Caption ref={lowerLabel} text={'under the hood'} />
            </Node>
          </Layout>
        </Layout>
        <Text ref={number} text={'#0'} fill={'#bb7c34'} fontSize={120} />
      </Layout>
      <Img ref={avatar} src={avatarSrc} size={150} radius={20} smoothCorners
           bottomRight={view.getOriginDelta(Origin.BottomRight).sub(40)} />
    </>
  );
  line().to([upperLabel().width(), 0]);
  const upperLabelText = upperLabel().text();

  prepare(upperLabel(), line(), avatar(), lowerLabel(), number(), lowerLabelWrapper());

  yield* waitUntil('start');

  yield delay(0.5, avatar().opacity(1, 2));

  yield upperLabel().text(upperLabelText, 1.5);
  yield* waitFor(0.25);

  yield all(
    line().start(0, 1.5),
    line().end(1, 1.5),
  );
  yield* waitFor(0.5);

  yield* lowerLabelWrapper().y(0, 1.5);

  yield* waitFor(1);
  yield* all(
    number().scale(1, 1.5),
    number().opacity(1, 1.5)
  );

  yield* waitUntil('end');
});

function prepare(upperLabel: Txt, line: Ray, avatar: Img, lowerLabel: Txt, number: Txt, lowerLabelWrapper: Node) {
  upperLabel.text('');
  line.start(0.5).end(0.5);
  lowerLabelWrapper.y(-lowerLabel.height());
  number.opacity(0).scale(2);
  avatar.opacity(0);
}
